package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class EmbedResponse {
  public String id;
  public List<Float> embeddings;

  public EmbedResponse() {
  }

  public EmbedResponse(String id, List<Float> embeddings) {
    this.embeddings = embeddings;
    this.id = id;
  }
}
