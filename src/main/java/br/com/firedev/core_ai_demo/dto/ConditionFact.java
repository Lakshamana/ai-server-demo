package br.com.firedev.core_ai_demo.dto;

// ConditionFact DTO
public class ConditionFact {
  private String conditionTypeId;
  private int conditionIndex;
  private String summary;
  private String reason;
  private Reference reference;

  // Constructors
  public ConditionFact() {
  }

  // Getters and Setters with chaining
  public String getConditionTypeId() {
    return conditionTypeId;
  }

  public ConditionFact setConditionTypeId(String conditionTypeId) {
    this.conditionTypeId = conditionTypeId;
    return this;
  }

  public int getConditionIndex() {
    return conditionIndex;
  }

  public ConditionFact setConditionIndex(int conditionIndex) {
    this.conditionIndex = conditionIndex;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public ConditionFact setSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public ConditionFact setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public Reference getReference() {
    return reference;
  }

  public ConditionFact setReference(Reference reference) {
    this.reference = reference;
    return this;
  }
}