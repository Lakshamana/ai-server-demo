package br.com.firedev.core_ai_demo.dto;

import java.util.List;
import java.util.ArrayList;

// DisplayName DTO
public class DisplayName {
  private List<DisplayNamePart> parts = new ArrayList<>();
  private String name;

  // Constructors
  public DisplayName() {
  }

  // Getters and Setters with chaining
  public List<DisplayNamePart> getParts() {
    return parts;
  }

  public DisplayName setParts(List<DisplayNamePart> parts) {
    this.parts = parts;
    return this;
  }

  public DisplayName addPart(DisplayNamePart part) {
    this.parts.add(part);
    return this;
  }

  public String getName() {
    return name;
  }

  public DisplayName setName(String name) {
    this.name = name;
    return this;
  }
}