package br.com.firedev.core_ai_demo.controller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.firedev.core_ai_demo.context.QueryContextHolder;
import br.com.firedev.core_ai_demo.dto.PromptChatRequest;
import br.com.firedev.core_ai_demo.interfaces.Assistant;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.service.AiServices;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

  private StreamingChatModel model;
  private Assistant assistant;
  private Logger logger;

  public ChatController() {
    this.logger = Logger.getLogger(ChatController.class.getName());

    this.model = OllamaStreamingChatModel.builder()
        .logResponses(true)
        .logRequests(true)
        .baseUrl("http://localhost:11434")
        .modelName("llama3")
        .build();

    this.assistant = AiServices.builder(Assistant.class)
        .streamingChatModel(this.model)
        .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
        .contentRetriever(query -> {
          List<TextSegment> userProvidedDocs = QueryContextHolder.getContext();

          if (userProvidedDocs != null && !userProvidedDocs.isEmpty()) {
            return userProvidedDocs.stream()
                .map(Content::from)
                .collect(Collectors.toList());
          }

          return Collections.emptyList();
        })
        .build();
  }

  @PostMapping
  public Flux<ServerSentEvent<Object>> chat(@RequestBody PromptChatRequest request) {
    String chatId = UUID.randomUUID().toString();

    logger.info("Chat id: " + chatId);
    logger.info("Request: " + request.toString());

    List<String> chunks = request.getChunks();
    if (chunks != null && !chunks.isEmpty()) {
      QueryContextHolder.setContext(
          chunks.stream()
              .map(TextSegment::from)
              .collect(Collectors.toList()));
    }

    return Flux
        .create(emitter -> {
          assistant.chat(request.getPrompt())
              .onPartialResponse(partial -> {
                emitter.next(ServerSentEvent.builder()
                    .event("eventId")
                    .id(UUID.randomUUID().toString())
                    .data(partial)
                    .build());
              })
              .onCompleteResponse(completeResponse -> {
                emitter.next(ServerSentEvent.builder()
                    .event("chat-token-" + chatId)
                    .id(completeResponse.id())
                    .data(completeResponse.aiMessage().text())
                    .build());

                QueryContextHolder.clear();
              })
              .onError(emitter::error)
              .start();
        });
  }
}
