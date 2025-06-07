package br.com.firedev.core_ai_demo.dto;

public class ChunkMetadata {
  private String filePath;
  private String type;
  private String name;
  private int startLine;
  private int endLine;
  private String language;

  public ChunkMetadata() {
  }

  public ChunkMetadata(
      String filePath,
      String fileType,
      String fileName,
      int startLine,
      int endLine,
      String language) {
    this.filePath = filePath;
    this.type = fileType;
    this.name = fileName;
    this.startLine = startLine;
    this.endLine = endLine;
    this.language = language;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getType() {
    return type;
  }

  public void setType(String fileType) {
    this.type = fileType;
  }

  public String getName() {
    return name;
  }

  public void setName(String fileName) {
    this.name = fileName;
  }

  public int getStartLine() {
    return startLine;
  }

  public void setStartLine(int startLine) {
    this.startLine = startLine;
  }

  public int getEndLine() {
    return endLine;
  }

  public void setEndLine(int endLine) {
    this.endLine = endLine;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }
}
