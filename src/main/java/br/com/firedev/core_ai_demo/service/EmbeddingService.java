package br.com.firedev.core_ai_demo.service;

import static br.com.firedev.core_ai_demo.singleton.EmbeddingConfiguration.EMBEDDING_MODEL;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.com.firedev.core_ai_demo.dto.CodeChunk;
import br.com.firedev.core_ai_demo.dto.EmbeddingResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service
public class EmbeddingService {

  private final Scheduler embeddingScheduler;
  private final AtomicInteger processedCount = new AtomicInteger(0);

  public EmbeddingService() {
    this.embeddingScheduler = Schedulers.newBoundedElastic(
        Runtime.getRuntime().availableProcessors() * 2,
        Integer.MAX_VALUE,
        "embedding-worker");
  }

  public EmbeddingResult embedPrompt(String text) {
    return new EmbeddingResult()
        .id("embedded-prompt")
        .status("completed")
        .content(text)
        .embedding(EMBEDDING_MODEL.embed(text).content().vectorAsList());
  }

  public Flux<EmbeddingResult> processChunksStreamWithProgress(String projectId, List<CodeChunk> chunks) {
    processedCount.set(0);
    final int totalChunks = chunks.size();

    return Flux.fromIterable(chunks)
        .flatMap(chunk -> processChunkWithProgressAsync(chunk, totalChunks), 4)
        .doOnNext(result -> System.out.println("Processed chunk with progress: " + result.getId()))
        .concatWith(Mono.just(EmbeddingResult.batchComplete(projectId, totalChunks)))
        .doOnError(error -> System.err.println("Progress stream error: " + error.getMessage()));
  }

  private Flux<EmbeddingResult> processChunkWithProgressAsync(CodeChunk chunk, int totalChunks) {
    return Mono.fromCallable(() -> {
      // Emit processing status
      int currentProcessed = processedCount.get();
      return EmbeddingResult.processing(chunk.getId(), totalChunks, currentProcessed);
    })
        .concatWith(
            Mono.fromCallable(() -> {
              try {
                // Simulate some processing time for demo
                Thread.sleep(100);

                List<Float> embedding = EMBEDDING_MODEL.embed(chunk.getContent())
                    .content()
                    .vectorAsList();

                int processed = processedCount.incrementAndGet();

                return EmbeddingResult.completed(
                    chunk.getId(),
                    chunk.getContent(),
                    embedding,
                    chunk.getMetadata(),
                    totalChunks,
                    processed);

              } catch (Exception e) {
                int processed = processedCount.incrementAndGet();
                return EmbeddingResult.error(chunk.getId(), e.getMessage(), totalChunks, processed);
              }
            })
                .subscribeOn(embeddingScheduler))
        .timeout(Duration.ofMinutes(3))
        .onErrorReturn(EmbeddingResult.error(
            chunk.getId(),
            "Processing timeout",
            totalChunks,
            processedCount.get()));
  }
}
