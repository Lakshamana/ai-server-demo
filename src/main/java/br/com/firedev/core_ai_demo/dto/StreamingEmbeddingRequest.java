package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class StreamingEmbeddingRequest {
  private String projectId;
  private List<CodeChunk> chunks;

  public StreamingEmbeddingRequest() {
  }

  public StreamingEmbeddingRequest(String projectId, List<CodeChunk> chunks) {
    this.projectId = projectId;
    this.chunks = chunks;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public List<CodeChunk> getChunks() {
    return chunks;
  }

  public void setChunks(List<CodeChunk> chunks) {
    this.chunks = chunks;
  }

  @Override
  public String toString() {
    return String.format(
        "StreamingEmbeddingRequest[projectId=%s, chunks={..., %d items}]",
        this.projectId,
        this.chunks.size());
  }
}
