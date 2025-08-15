package br.com.firedev.core_ai_demo.dto;

import java.util.List;
import java.util.ArrayList;

// ConstraintFact DTO
public class ConstraintFact {
  private String constraintId;
  private String constraintName;
  private String operatorName;
  private List<ConditionFact> conditionFacts = new ArrayList<>();

  // Constructors
  public ConstraintFact() {
  }

  // Getters and Setters with chaining
  public String getConstraintId() {
    return constraintId;
  }

  public ConstraintFact setConstraintId(String constraintId) {
    this.constraintId = constraintId;
    return this;
  }

  public String getConstraintName() {
    return constraintName;
  }

  public ConstraintFact setConstraintName(String constraintName) {
    this.constraintName = constraintName;
    return this;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public ConstraintFact setOperatorName(String operatorName) {
    this.operatorName = operatorName;
    return this;
  }

  public List<ConditionFact> getConditionFacts() {
    return conditionFacts;
  }

  public ConstraintFact setConditionFacts(List<ConditionFact> conditionFacts) {
    this.conditionFacts = conditionFacts;
    return this;
  }

  public ConstraintFact addConditionFact(ConditionFact conditionFact) {
    this.conditionFacts.add(conditionFact);
    return this;
  }
}