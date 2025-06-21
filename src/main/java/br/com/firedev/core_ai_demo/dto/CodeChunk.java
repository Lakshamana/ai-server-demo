package br.com.firedev.core_ai_demo.dto;

public class CodeChunk {
  private String id;
  private String content;
  private ChunkMetadata metadata;
  private Float relevanceScore;

  public CodeChunk() {
  }

  public CodeChunk(String id, String content, ChunkMetadata metadata) {
    this.id = id;
    this.content = content;
    this.metadata = metadata;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ChunkMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(ChunkMetadata metadata) {
    this.metadata = metadata;
  }

  public Float getRelevanceScore() {
    return relevanceScore;
  }

  public void setRelevanceScore(Float relevanceScore) {
    this.relevanceScore = relevanceScore;
  }

  @Override
  public String toString() {
    return "CodeChunk{" +
        "id='" + id + '\'' +
        ", content='" + content + '\'' +
        ", relevanceScore=" + relevanceScore +
        '}';
  }
}
