package br.com.firedev.core_ai_demo.dto;

public class CreateEntityInput {

  private String entityName;
  private String attributes;
  private String client;

  public String getClient() {
    return client;
  }

  public CreateEntityInput(String entityName, String attributes, String clientType) {
    this.entityName = entityName;
    this.attributes = attributes;
    this.client = clientType;
  }

  public void setClient(String clientType) {
    this.client = clientType;
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  public String getAttributes() {
    return attributes;
  }

  public void setAttributes(String attributes) {
    this.attributes = attributes;
  }
}
