package br.com.firedev.core_ai_demo.dto;

public class QueryRequest {
  private String query;
  private int maxResults;

  public String getQuery() {
    return query;
  }
  public void setQuery(String query) {
    this.query = query;
  }
  public int getMaxResults() {
    return maxResults;
  }
  public void setMaxResults(int maxResults) {
    this.maxResults = maxResults;
  }

  @Override
  public String toString() {
    return String.format("QueryRequest[query=%s, maxResults=%d]", this.query, this.maxResults);
  }
}
