package br.com.firedev.core_ai_demo.dto;

public class RefactorCodeInput extends ExplainCodeInput {

  private String codeToRefactor;

  public String getCodeToRefactor() {
    return codeToRefactor;
  }

  public void setCodeToRefactor(String codeToRefactor) {
    this.codeToRefactor = codeToRefactor;
  }

  RefactorCodeInput(String fileContent, String codeToRefactor) {
    super(fileContent);
    this.codeToRefactor = codeToRefactor;
  }
}
