package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class PromptChatRequest {
  String prompt;
  List<String> chunks;

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public List<String> getChunks() {
    return chunks;
  }

  public void setChunks(List<String> chunks) {
    this.chunks = chunks;
  }

  @Override
  public String toString() {
    return "PromptChatRequest[prompt=" + prompt + ", chunks=" + chunks + "]";
  }
}
