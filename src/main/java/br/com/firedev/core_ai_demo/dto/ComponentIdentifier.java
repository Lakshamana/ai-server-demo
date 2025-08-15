package br.com.firedev.core_ai_demo.dto;

// ComponentIdentifier DTO
public class ComponentIdentifier {
  private String format;
  private MavenCoordinates coordinates;

  // Constructors
  public ComponentIdentifier() {
  }

  // Getters and Setters with chaining
  public String getFormat() {
    return format;
  }

  public ComponentIdentifier setFormat(String format) {
    this.format = format;
    return this;
  }

  public MavenCoordinates getCoordinates() {
    return coordinates;
  }

  public ComponentIdentifier setCoordinates(MavenCoordinates coordinates) {
    this.coordinates = coordinates;
    return this;
  }
}