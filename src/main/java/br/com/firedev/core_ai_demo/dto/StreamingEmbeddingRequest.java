package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class StreamingEmbeddingRequest {
  private String projectId;
  private List<CodeChunkRequest> chunks;

  public StreamingEmbeddingRequest() {
  }

  public StreamingEmbeddingRequest(String projectId, List<CodeChunkRequest> chunks) {
    this.projectId = projectId;
    this.chunks = chunks;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public List<CodeChunkRequest> getChunks() {
    return chunks;
  }

  public void setChunks(List<CodeChunkRequest> chunks) {
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
