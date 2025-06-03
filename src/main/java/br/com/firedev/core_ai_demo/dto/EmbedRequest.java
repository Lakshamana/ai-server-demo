package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class EmbedRequest {
  private List<String> texts;

  public EmbedRequest() {
  }

  public EmbedRequest(List<String> texts) {
    this.texts = texts;
  }

  public List<String> getTexts() {
    return texts;
  }
}
