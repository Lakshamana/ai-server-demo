package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class EmbeddingResult {
  private String id;
  private String content;
  private List<Float> embedding;
  private ChunkMetadata metadata;
  private String status; // "processing", "completed", "error"
  private String error;
  private long timestamp;
  private int totalChunks;
  private int processedChunks;

  public EmbeddingResult() {
    this.timestamp = System.currentTimeMillis();
  }

  public EmbeddingResult id(String id) {
    this.id = id;
    return this;
  }

  public EmbeddingResult content(String content) {
    this.content = content;
    return this;
  }

  public EmbeddingResult embedding(List<Float> embedding) {
    this.embedding = embedding;
    return this;
  }

  public EmbeddingResult metadata(ChunkMetadata metadata) {
    this.metadata = metadata;
    return this;
  }

  public EmbeddingResult status(String status) {
    this.status = status;
    return this;
  }

  public EmbeddingResult error(String error) {
    this.error = error;
    return this;
  }

  public EmbeddingResult timestamp(long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public EmbeddingResult totalChunks(int totalChunks) {
    this.totalChunks = totalChunks;
    return this;
  }

  public EmbeddingResult processedChunks(int processedChunks) {
    this.processedChunks = processedChunks;
    return this;
  }

  public EmbeddingResult(String id, String content, List<Float> embedding,
      ChunkMetadata metadata, String status) {
    this();
    this.id = id;
    this.content = content;
    this.embedding = embedding;
    this.metadata = metadata;
    this.status = status;
  }

  public static EmbeddingResult processing(String id, int totalChunks, int processedChunks) {
    return new EmbeddingResult()
        .id(id)
        .totalChunks(totalChunks)
        .processedChunks(processedChunks)
        .status("processing");
  }

  public static EmbeddingResult completed(
      String id, String content, List<Float> embedding,
      ChunkMetadata metadata, int totalChunks, int processedChunks) {
    return new EmbeddingResult(id, content, embedding, metadata, "completed")
        .totalChunks(totalChunks)
        .processedChunks(processedChunks);
  }

  public static EmbeddingResult error(String id, String error, int totalChunks, int processedChunks) {
    return new EmbeddingResult()
        .id(id)
        .totalChunks(totalChunks)
        .processedChunks(processedChunks)
        .status("error");
  }

  public static EmbeddingResult batchComplete(String projectId, int totalChunks) {
    return new EmbeddingResult()
        .id(projectId)
        .totalChunks(totalChunks)
        .status("batch_complete");
  }

  public String getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public List<Float> getEmbedding() {
    return embedding;
  }

  public ChunkMetadata getMetadata() {
    return metadata;
  }

  public String getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public int getTotalChunks() {
    return totalChunks;
  }

  public int getProcessedChunks() {
    return processedChunks;
  }
}
