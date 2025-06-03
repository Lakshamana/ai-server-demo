package br.com.firedev.core_ai_demo.dto;

public class QueryResultItem {
  private Double score;
  private String id;
  private String text;

  public QueryResultItem(Double score, String id, String text) {
    this.score = score;
    this.id = id;
    this.text = text;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
