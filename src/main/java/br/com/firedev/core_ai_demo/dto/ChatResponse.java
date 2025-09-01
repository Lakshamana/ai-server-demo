package br.com.firedev.core_ai_demo.dto;

public class ChatResponse {

  public ChatResponse(String aiMsg) {
    this.aiMsg = aiMsg;
  }

  private String aiMsg;

  public String getAiMsg() {
    return aiMsg;
  }

  public void setAiMsg(String aiMsg) {
    this.aiMsg = aiMsg;
  }
}
