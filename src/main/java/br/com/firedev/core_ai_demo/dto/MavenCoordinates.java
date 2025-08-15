package br.com.firedev.core_ai_demo.dto;

// MavenCoordinates DTO
public class MavenCoordinates {
  private String artifactId;
  private String classifier;
  private String extension;
  private String groupId;
  private String version;

  // Constructors
  public MavenCoordinates() {
  }

  // Getters and Setters with chaining
  public String getArtifactId() {
    return artifactId;
  }

  public MavenCoordinates setArtifactId(String artifactId) {
    this.artifactId = artifactId;
    return this;
  }

  public String getClassifier() {
    return classifier;
  }

  public MavenCoordinates setClassifier(String classifier) {
    this.classifier = classifier;
    return this;
  }

  public String getExtension() {
    return extension;
  }

  public MavenCoordinates setExtension(String extension) {
    this.extension = extension;
    return this;
  }

  public String getGroupId() {
    return groupId;
  }

  public MavenCoordinates setGroupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  public String getVersion() {
    return version;
  }

  public MavenCoordinates setVersion(String version) {
    this.version = version;
    return this;
  }
}