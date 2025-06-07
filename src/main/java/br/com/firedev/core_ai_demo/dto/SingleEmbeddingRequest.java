package br.com.firedev.core_ai_demo.dto;

public class SingleEmbeddingRequest {
  String prompt;

  public String getPrompt() {
    return prompt;
  }

  @Override
  public String toString() {
    return "SingleEmbeddingRequest [prompt=" + prompt + "]";
  }
}
