package br.com.firedev.core_ai_demo.dto;

import java.util.List;

public class ChatRequest {
  String userMsg;
  Long chatId;
  List<CodeChunk> chunks;
  UserPrompt userPromptTemplate;

  public UserPrompt getUserPromptTemplate() {
    return userPromptTemplate;
  }

  public void setUserPromptTemplate(UserPrompt userPromptTemplate) {
    this.userPromptTemplate = userPromptTemplate;
  }

  public String getUserMsg() {
    return userMsg;
  }

  public void setUserMsg(String userMsg) {
    this.userMsg = userMsg;
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
    return "ChatRequest[userMsg=" + userMsg + ", chunks=" + chunks + ", userPromptTemplate=" + userPromptTemplate + "]";
  }
}
