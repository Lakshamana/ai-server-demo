package br.com.firedev.core_ai_demo.dto;

public class ExplainCodeInput {

  private String fileContent;

  public ExplainCodeInput(String fileContent) {
    this.fileContent = fileContent;
  }

  public String getFileContent() {
    return fileContent;
  }

  public void setFileContent(String fileContent) {
    this.fileContent = fileContent;
  }
}
