package br.com.firedev.core_ai_demo.dto;

import java.util.List;
import java.util.ArrayList;

// ComponentFact DTO
public class ComponentFact {
  private ComponentIdentifier componentIdentifier;
  private String hash;
  private List<ConstraintFact> constraintFacts = new ArrayList<>();
  private DisplayName displayName;

  // Constructors
  public ComponentFact() {
  }

  // Getters and Setters with chaining
  public ComponentIdentifier getComponentIdentifier() {
    return componentIdentifier;
  }

  public ComponentFact setComponentIdentifier(ComponentIdentifier componentIdentifier) {
    this.componentIdentifier = componentIdentifier;
    return this;
  }

  public String getHash() {
    return hash;
  }

  public ComponentFact setHash(String hash) {
    this.hash = hash;
    return this;
  }

  public List<ConstraintFact> getConstraintFacts() {
    return constraintFacts;
  }

  public ComponentFact setConstraintFacts(List<ConstraintFact> constraintFacts) {
    this.constraintFacts = constraintFacts;
    return this;
  }

  public ComponentFact addConstraintFact(ConstraintFact constraintFact) {
    this.constraintFacts.add(constraintFact);
    return this;
  }

  public DisplayName getDisplayName() {
    return displayName;
  }

  public ComponentFact setDisplayName(DisplayName displayName) {
    this.displayName = displayName;
    return this;
  }
}