package br.com.firedev.core_ai_demo.service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;

import br.com.firedev.core_ai_demo.constant.Prompts;
import br.com.firedev.core_ai_demo.dto.ChatRequest;
import br.com.firedev.core_ai_demo.dto.ChunkMetadata;
import br.com.firedev.core_ai_demo.dto.CodeChunk;
import br.com.firedev.core_ai_demo.dto.UserPrompt;
import br.com.firedev.core_ai_demo.interfaces.Assistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.service.AiServices;
import reactor.core.publisher.Flux;

@Service
public class AssistantService {

  private final StreamingChatModel model;
  private final Logger logger;

  public AssistantService() {
    this.logger = Logger.getLogger(AssistantService.class.getName());

    this.model = OllamaStreamingChatModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("phi3:mini")
        .logRequests(true)
        .logResponses(true)
        .timeout(Duration.ofMinutes(3))
        .build();
  }

  private Assistant buildAssistant(List<CodeChunk> context, UserPrompt userPromptTemplate) {
    return AiServices.builder(Assistant.class)
        .streamingChatModel(this.model)
        .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
        .systemMessageProvider(provider -> buildSystemMessage(provider, userPromptTemplate))
        .contentRetriever(query -> retrieveAndFormatContext(query, context))
        .build();
  }

  private String buildSystemMessage(Object provider, UserPrompt userPromptTemplate) {
    return userPromptTemplate != null
      ? Prompts.buildSystemMessageFromUserPrompt(userPromptTemplate)
      : Prompts.ASSISTANT_SYSTEM_MESSAGE;
  }

  private List<Content> retrieveAndFormatContext(Query query, List<CodeChunk> context) {
    if (context == null || context.isEmpty()) {
      return Collections.emptyList();
    }

    // Build rich context with metadata
    StringBuilder contextBuilder = new StringBuilder();
    contextBuilder.append("## Code Context\n\n");

    // Group chunks by file for better organization
    Map<String, List<CodeChunk>> chunksByFile = groupChunksByFile(context);

    for (Map.Entry<String, List<CodeChunk>> entry : chunksByFile.entrySet()) {
      String filePath = entry.getKey();
      List<CodeChunk> chunks = entry.getValue();

      contextBuilder.append(String.format("### File: %s\n", filePath));

      // Add file-level metadata
      if (!chunks.isEmpty()) {
        ChunkMetadata firstChunk = chunks.get(0).getMetadata();
        contextBuilder.append(String.format("- **Language**: %s\n", firstChunk.getLanguage()));
        contextBuilder.append(String.format("- **Type**: %s\n", firstChunk.getType()));
      }

      // Add relevance information if available
      boolean hasRelevanceScores = chunks.stream()
          .anyMatch(chunk -> chunk.getRelevanceScore() != null);

      if (hasRelevanceScores) {
        contextBuilder.append("- **Source**: Vector similarity search\n");
        // Sort by relevance score for better context ordering
        chunks.sort((a, b) -> Float.compare(
            b.getRelevanceScore() != null ? b.getRelevanceScore() : 0f,
            a.getRelevanceScore() != null ? a.getRelevanceScore() : 0f));
      } else {
        contextBuilder.append("- **Source**: User-provided file\n");
      }

      contextBuilder.append("\n");

      // Add each code chunk with detailed metadata
      for (CodeChunk chunk : chunks) {
        contextBuilder.append("```").append(chunk.getMetadata().getLanguage()).append("\n");
        contextBuilder.append(String.format("// %s (lines %d-%d)",
            chunk.getMetadata().getName(),
            chunk.getMetadata().getStartLine(),
            chunk.getMetadata().getEndLine()));

        if (chunk.getRelevanceScore() != null) {
          contextBuilder.append(String.format(" [Relevance: %.2f]", chunk.getRelevanceScore()));
        }
        contextBuilder.append("\n");

        contextBuilder.append(chunk.getContent()).append("\n");
        contextBuilder.append("```\n\n");
      }
    }

    return List.of(Content.from(contextBuilder.toString()));
  }

  private Map<String, List<CodeChunk>> groupChunksByFile(List<CodeChunk> chunks) {
    return chunks.stream()
        .filter(chunk -> chunk.getMetadata() != null)
        .collect(Collectors.groupingBy(
            chunk -> chunk.getMetadata().getFilePath(),
            LinkedHashMap::new,
            Collectors.toList()));
  }

  public Flux<ServerSentEvent<String>> chat(ChatRequest request) {
    Assistant assistant = buildAssistant(request.getChunks(), request.getUserPromptTemplate());

    return Flux
        .create(sink -> {
          sink.next(ServerSentEvent.<String>builder()
              .event("chatId")
              .id(UUID.randomUUID().toString())
              .data(String.valueOf(1L))
              .build());

          assistant.chat(request.getPrompt())
              .onPartialResponse(partial -> {
                logger.info(String.format("Partial response: '%s'", formatMessage(partial)));
                sink.next(ServerSentEvent.<String>builder()
                    .event("token")
                    .id(UUID.randomUUID().toString())
                    .data(formatMessage(partial))
                    .build());
              })
              .onCompleteResponse(completeResponse -> {
                logger.info("Complete response: " + completeResponse);
                sink.complete();
              })
              .onError(sink::error)
              .start();
        });
  }

  private static String formatMessage(String event) {
    var message = (event != null)
        ? new String(event.getBytes(), StandardCharsets.UTF_8)
        : "";
    return "_" + message;
  }
}
