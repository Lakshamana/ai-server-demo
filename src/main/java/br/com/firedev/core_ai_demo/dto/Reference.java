package br.com.firedev.core_ai_demo.dto;

// Reference DTO
public class Reference {
  private String value;
  private String type;

  // Constructors
  public Reference() {
  }

  // Getters and Setters with chaining
  public String getValue() {
    return value;
  }

  public Reference setValue(String value) {
    this.value = value;
    return this;
  }

  public String getType() {
    return type;
  }

  public Reference setType(String type) {
    this.type = type;
    return this;
  }
}