package br.com.firedev.core_ai_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.firedev.core_ai_demo.dto.ChatRequest;
import br.com.firedev.core_ai_demo.dto.EmbeddingResult;
import br.com.firedev.core_ai_demo.dto.SingleEmbeddingRequest;
import br.com.firedev.core_ai_demo.dto.StreamingEmbeddingRequest;
import br.com.firedev.core_ai_demo.service.AssistantService;
import br.com.firedev.core_ai_demo.service.EmbeddingService;
import br.com.firedev.core_ai_demo.utils.EventUtils;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

  private Logger logger;

  @Autowired
  private AssistantService assistantService;

  @Autowired
  private EmbeddingService embeddingService;

  public ChatController() {
    this.logger = LoggerFactory.getLogger(this.getClass().getName());
  }

  @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<String>> chat(@RequestBody ChatRequest request) {

    logger.info("Chat id: " + request.getChatId());
    logger.info("Request: " + request.toString());

    return assistantService.chat(request);
  }

  @PostMapping(value = "/embed-prompt", consumes = MediaType.APPLICATION_JSON_VALUE)
  public EmbeddingResult embedPrompt(@RequestBody SingleEmbeddingRequest request) {
    logger.info("Received single embedding request: {}", request);

    return embeddingService.embedPrompt(request.getPrompt());
  }

  @PostMapping(value = "/setup", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<EmbeddingResult>> streamEmbeddings(
      @RequestBody StreamingEmbeddingRequest request) {

    logger.info("Received stream request: {}", request);

    return embeddingService.processChunksStreamWithProgress(request.getProjectId(), request.getChunks())
        .map(result -> {
          String eventType = EventUtils.determineEventType(result.getStatus());

          return ServerSentEvent.<EmbeddingResult>builder()
              .id(result.getId())
              .event(eventType)
              .data(result)
              .comment(EventUtils.createProgressComment(result))
              .build();
        })
        .doOnError(error -> System.err.println("Streaming error:" + error.getMessage()));
  }

}
