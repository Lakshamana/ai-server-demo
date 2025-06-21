package br.com.firedev.core_ai_demo.dto;

public class UserPrompt {
  private String name;
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UserPrompt() {
  }

  public UserPrompt(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public String toString() {
    return "UserPrompt [name=" + name + ", description=" + description + "]";
  }
}
