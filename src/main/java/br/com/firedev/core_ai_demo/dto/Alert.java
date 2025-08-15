package br.com.firedev.core_ai_demo.dto;

import java.util.List;
import java.util.ArrayList;

// Alert DTO
public class Alert {
  private Trigger trigger;
  private List<Object> actions = new ArrayList<>();

  // Constructors
  public Alert() {
  }

  // Getters and Setters with chaining
  public Trigger getTrigger() {
    return trigger;
  }

  public Alert setTrigger(Trigger trigger) {
    this.trigger = trigger;
    return this;
  }

  public List<Object> getActions() {
    return actions;
  }

  public Alert setActions(List<Object> actions) {
    this.actions = actions;
    return this;
  }

  public Alert addAction(Object action) {
    this.actions.add(action);
    return this;
  }
}