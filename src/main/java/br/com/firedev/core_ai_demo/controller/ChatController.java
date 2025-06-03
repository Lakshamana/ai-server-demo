package br.com.firedev.core_ai_demo.controller;

import static br.com.firedev.core_ai_demo.singleton.EmbeddingStore.embeddingStore;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.firedev.core_ai_demo.interfaces.Assistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
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
        .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
        .build();
  }

  @GetMapping
  public Flux<ServerSentEvent<Object>> chat(@RequestParam String prompt) {
    String chatId = UUID.randomUUID().toString();

    logger.info("Chat id: " + chatId);

    return Flux.create(emitter -> {
      assistant.chat(prompt)
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
          })
          .onError(emitter::error)
          .start();
    });
  }
}
