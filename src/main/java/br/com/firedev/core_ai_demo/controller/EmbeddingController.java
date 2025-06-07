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

import br.com.firedev.core_ai_demo.dto.EmbeddingResult;
import br.com.firedev.core_ai_demo.dto.SingleEmbeddingRequest;
import br.com.firedev.core_ai_demo.dto.StreamingEmbeddingRequest;
import br.com.firedev.core_ai_demo.service.EmbeddingService;
import br.com.firedev.core_ai_demo.utils.EventUtils;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/embeddings")
public class EmbeddingController {

  private final Logger logger;

  @Autowired
  private EmbeddingService embeddingService;

  public EmbeddingController() {
    this.logger = LoggerFactory.getLogger(EmbeddingController.class);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public EmbeddingResult embedPrompt(@RequestBody SingleEmbeddingRequest request) {
    logger.info("Received single embedding request: {}", request);

    return embeddingService.embedPrompt(request.getPrompt());
  }

  @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<EmbeddingResult>> streamEmbeddingsHybrid(
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

  // @GetMapping
  // public List<QueryResultItem> query(QueryRequest request) {
  // logger.info("Listing embeddings: {}", request.toString());
  //
  // List<EmbeddingMatch<TextSegment>> matches = EMBEDDING_STORE.search(
  // EmbeddingSearchRequest.builder()
  // .queryEmbedding(EMBEDDING_MODEL.embed(request.getQuery()).content())
  // .maxResults(request.getMaxResults())
  // .build())
  // .matches();
  //
  // logger.info("Matches: {}", matches);
  //
  // return matches.stream()
  // .filter(m -> m.embedded() != null)
  // .map(m -> new QueryResultItem(m.score(), m.embeddingId(),
  // m.embedded().text()))
  // .collect(Collectors.toList());
  // }
  //
  // private ResponseEntity<Object> destroyInstanceAndRespond() {
  // return ResponseEntity.status(HttpStatus.CREATED).build();
  // }
  //
  // private Mono<Void> saveChunk(List<String> chunk) {
  // return Flux.fromIterable(chunk)
  // .map(TextSegment::from)
  // .flatMap(
  // textSegment -> Mono.fromRunnable(
  // () -> {
  // logger.info("Saving chunk: {}", textSegment.text());
  // EMBEDDING_STORE.add(EMBEDDING_MODEL.embed(textSegment).content(),
  // textSegment);
  // }).subscribeOn(Schedulers.boundedElastic()))
  // .then();
  // }
}
