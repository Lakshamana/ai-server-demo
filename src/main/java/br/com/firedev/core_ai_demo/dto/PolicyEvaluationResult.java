package br.com.firedev.core_ai_demo.dto;

import java.util.List;
import java.util.ArrayList;

// Policy evaluation result DTO
public class PolicyEvaluationResult {
  private List<Alert> alerts = new ArrayList<>();
  private int affectedComponentCount;
  private int criticalComponentCount;
  private int severeComponentCount;
  private int moderateComponentCount;
  private int criticalPolicyViolationCount;
  private int severePolicyViolationCount;
  private int moderatePolicyViolationCount;
  private int legacyViolationCount;
  private int totalComponentCount;
  private List<Object> sastAlerts = new ArrayList<>();
  private int criticalSastPolicyViolationCount;
  private int severeSastPolicyViolationCount;
  private int moderateSastPolicyViolationCount;
  private int totalSastFindingCount;
  private int grandfatheredPolicyViolationCount;

  // Constructors
  public PolicyEvaluationResult() {
  }

  // Getters and Setters with chaining
  public List<Alert> getAlerts() {
    return alerts;
  }

  public PolicyEvaluationResult setAlerts(List<Alert> alerts) {
    this.alerts = alerts;
    return this;
  }

  public PolicyEvaluationResult addAlert(Alert alert) {
    this.alerts.add(alert);
    return this;
  }

  public int getAffectedComponentCount() {
    return affectedComponentCount;
  }

  public PolicyEvaluationResult setAffectedComponentCount(int affectedComponentCount) {
    this.affectedComponentCount = affectedComponentCount;
    return this;
  }

  public int getCriticalComponentCount() {
    return criticalComponentCount;
  }

  public PolicyEvaluationResult setCriticalComponentCount(int criticalComponentCount) {
    this.criticalComponentCount = criticalComponentCount;
    return this;
  }

  public int getSevereComponentCount() {
    return severeComponentCount;
  }

  public PolicyEvaluationResult setSevereComponentCount(int severeComponentCount) {
    this.severeComponentCount = severeComponentCount;
    return this;
  }

  public int getModerateComponentCount() {
    return moderateComponentCount;
  }

  public PolicyEvaluationResult setModerateComponentCount(int moderateComponentCount) {
    this.moderateComponentCount = moderateComponentCount;
    return this;
  }

  public int getCriticalPolicyViolationCount() {
    return criticalPolicyViolationCount;
  }

  public PolicyEvaluationResult setCriticalPolicyViolationCount(int criticalPolicyViolationCount) {
    this.criticalPolicyViolationCount = criticalPolicyViolationCount;
    return this;
  }

  public int getSeverePolicyViolationCount() {
    return severePolicyViolationCount;
  }

  public PolicyEvaluationResult setSeverePolicyViolationCount(int severePolicyViolationCount) {
    this.severePolicyViolationCount = severePolicyViolationCount;
    return this;
  }

  public int getModeratePolicyViolationCount() {
    return moderatePolicyViolationCount;
  }

  public PolicyEvaluationResult setModeratePolicyViolationCount(int moderatePolicyViolationCount) {
    this.moderatePolicyViolationCount = moderatePolicyViolationCount;
    return this;
  }

  public int getLegacyViolationCount() {
    return legacyViolationCount;
  }

  public PolicyEvaluationResult setLegacyViolationCount(int legacyViolationCount) {
    this.legacyViolationCount = legacyViolationCount;
    return this;
  }

  public int getTotalComponentCount() {
    return totalComponentCount;
  }

  public PolicyEvaluationResult setTotalComponentCount(int totalComponentCount) {
    this.totalComponentCount = totalComponentCount;
    return this;
  }

  public List<Object> getSastAlerts() {
    return sastAlerts;
  }

  public PolicyEvaluationResult setSastAlerts(List<Object> sastAlerts) {
    this.sastAlerts = sastAlerts;
    return this;
  }

  public int getCriticalSastPolicyViolationCount() {
    return criticalSastPolicyViolationCount;
  }

  public PolicyEvaluationResult setCriticalSastPolicyViolationCount(int criticalSastPolicyViolationCount) {
    this.criticalSastPolicyViolationCount = criticalSastPolicyViolationCount;
    return this;
  }

  public int getSevereSastPolicyViolationCount() {
    return severeSastPolicyViolationCount;
  }

  public PolicyEvaluationResult setSevereSastPolicyViolationCount(int severeSastPolicyViolationCount) {
    this.severeSastPolicyViolationCount = severeSastPolicyViolationCount;
    return this;
  }

  public int getModerateSastPolicyViolationCount() {
    return moderateSastPolicyViolationCount;
  }

  public PolicyEvaluationResult setModerateSastPolicyViolationCount(int moderateSastPolicyViolationCount) {
    this.moderateSastPolicyViolationCount = moderateSastPolicyViolationCount;
    return this;
  }

  public int getTotalSastFindingCount() {
    return totalSastFindingCount;
  }

  public PolicyEvaluationResult setTotalSastFindingCount(int totalSastFindingCount) {
    this.totalSastFindingCount = totalSastFindingCount;
    return this;
  }

  public int getGrandfatheredPolicyViolationCount() {
    return grandfatheredPolicyViolationCount;
  }

  public PolicyEvaluationResult setGrandfatheredPolicyViolationCount(int grandfatheredPolicyViolationCount) {
    this.grandfatheredPolicyViolationCount = grandfatheredPolicyViolationCount;
    return this;
  }
}