package br.com.firedev.core_ai_demo.dto;

import java.util.List;
import java.util.ArrayList;

// Trigger DTO
public class Trigger {
  private String policyId;
  private String policyName;
  private int threatLevel;
  private String policyViolationId;
  private List<ComponentFact> componentFacts = new ArrayList<>();

  // Constructors
  public Trigger() {
  }

  // Getters and Setters with chaining
  public String getPolicyId() {
    return policyId;
  }

  public Trigger setPolicyId(String policyId) {
    this.policyId = policyId;
    return this;
  }

  public String getPolicyName() {
    return policyName;
  }

  public Trigger setPolicyName(String policyName) {
    this.policyName = policyName;
    return this;
  }

  public int getThreatLevel() {
    return threatLevel;
  }

  public Trigger setThreatLevel(int threatLevel) {
    this.threatLevel = threatLevel;
    return this;
  }

  public String getPolicyViolationId() {
    return policyViolationId;
  }

  public Trigger setPolicyViolationId(String policyViolationId) {
    this.policyViolationId = policyViolationId;
    return this;
  }

  public List<ComponentFact> getComponentFacts() {
    return componentFacts;
  }

  public Trigger setComponentFacts(List<ComponentFact> componentFacts) {
    this.componentFacts = componentFacts;
    return this;
  }

  public Trigger addComponentFact(ComponentFact componentFact) {
    this.componentFacts.add(componentFact);
    return this;
  }
}