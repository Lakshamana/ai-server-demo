package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class ChatRequest {
  String prompt;
  Long chatId;
  List<CodeChunk> chunks;
  UserPrompt userPromptTemplate;

  public UserPrompt getUserPromptTemplate() {
    return userPromptTemplate;
  }

  public void setUserPromptTemplate(UserPrompt userPromptTemplate) {
    this.userPromptTemplate = userPromptTemplate;
  }

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
    return "ChatRequest[prompt=" + prompt + ", chunks=" + chunks + ", userPromptTemplate=" + userPromptTemplate + "]";
  }
}
