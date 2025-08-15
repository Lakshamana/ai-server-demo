package br.com.firedev.core_ai_demo.dto;

// DisplayNamePart DTO
public class DisplayNamePart {
  private String field;
  private String value;

  // Constructors
  public DisplayNamePart() {
  }

  public DisplayNamePart(String field, String value) {
    this.field = field;
    this.value = value;
  }

  // Getters and Setters with chaining
  public String getField() {
    return field;
  }

  public DisplayNamePart setField(String field) {
    this.field = field;
    return this;
  }

  public String getValue() {
    return value;
  }

  public DisplayNamePart setValue(String value) {
    this.value = value;
    return this;
  }
}