package br.com.firedev.core_ai_demo.controller;

import static br.com.firedev.core_ai_demo.singleton.EmbeddingModel.embeddingModel;
import static br.com.firedev.core_ai_demo.singleton.EmbeddingStore.embeddingStore;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.firedev.core_ai_demo.dto.EmbedRequest;
import br.com.firedev.core_ai_demo.dto.QueryRequest;
import br.com.firedev.core_ai_demo.dto.QueryResultItem;
import br.com.firedev.core_ai_demo.utils.Partition;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/embed")
public class EmbedController {

  private final Logger logger;
  private final int chunkSize = 20;

  public EmbedController() {
    this.logger = LoggerFactory.getLogger(EmbedController.class);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<Object>> embed(@RequestBody EmbedRequest request) {
    logger.info(String.format("Embedding request: EmbedRequest[texts={...%d items}]", request.getTexts().size()));

    if (request.getTexts().isEmpty()) {
      return Mono.just(ResponseEntity.badRequest().build());
    }

    return Flux.fromIterable(Partition.ofSize(request.getTexts(), chunkSize))
        .flatMap(this::saveChunk)
        .then()
        .timeout(Duration.ofSeconds(10))
        .thenReturn(this.destroyInstanceAndRespond())
        .onErrorReturn(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build());
  }

  @GetMapping
  public List<QueryResultItem> query(QueryRequest request) {
    logger.info("Listing embeddings: {}", request.toString());

    List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(
        EmbeddingSearchRequest.builder()
            .queryEmbedding(embeddingModel.embed(request.getQuery()).content())
            .maxResults(request.getMaxResults())
            .build())
        .matches();

    logger.info("Matches: {}", matches);

    return matches.stream()
        .filter(m -> m.embedded() != null)
        .map(m -> new QueryResultItem(m.score(), m.embeddingId(), m.embedded().text()))
        .collect(Collectors.toList());
  }

  private ResponseEntity<Object> destroyInstanceAndRespond() {
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  private Mono<Void> saveChunk(List<String> chunk) {
    return Flux.fromIterable(chunk)
        .map(TextSegment::from)
        .flatMap(
            textSegment -> Mono.fromRunnable(
                () -> {
                  logger.info("Saving chunk: {}", textSegment.text());
                  embeddingStore.add(embeddingModel.embed(textSegment).content(), textSegment);
                }).subscribeOn(Schedulers.boundedElastic()))
        .then();
  }
}
