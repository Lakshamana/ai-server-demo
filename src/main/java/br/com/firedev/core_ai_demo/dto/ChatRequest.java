package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class ChatRequest {
  String prompt;
  Long chatId;
  List<CodeChunk> chunks;

  public String getPrompt() {
    return prompt;
  }

  public void setPrompt(String prompt) {
    this.prompt = prompt;
  }

  public List<CodeChunk> getChunks() {
    return chunks;
  }

  public void setChunks(List<CodeChunk> chunks) {
    this.chunks = chunks;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  public Long getChatId() {
    return chatId;
  }

  @Override
  public String toString() {
    return "PromptChatRequest[prompt=" + prompt + ", chunks=" + chunks + "]";
  }
}
