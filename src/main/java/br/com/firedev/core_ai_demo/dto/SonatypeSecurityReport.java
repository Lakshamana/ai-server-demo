package br.com.firedev.core_ai_demo.dto;

// Main response DTO
public class SonatypeSecurityReport {
  private String applicationId;
  private String scanId;
  private String reportHtmlUrl;
  private String reportPdfUrl;
  private String reportDataUrl;
  private String policyAction;
  private PolicyEvaluationResult policyEvaluationResult;

  // Constructors
  public SonatypeSecurityReport() {
  }

  // Getters and Setters with chaining
  public String getApplicationId() {
    return applicationId;
  }

  public SonatypeSecurityReport setApplicationId(String applicationId) {
    this.applicationId = applicationId;
    return this;
  }

  public String getScanId() {
    return scanId;
  }

  public SonatypeSecurityReport setScanId(String scanId) {
    this.scanId = scanId;
    return this;
  }

  public String getReportHtmlUrl() {
    return reportHtmlUrl;
  }

  public SonatypeSecurityReport setReportHtmlUrl(String reportHtmlUrl) {
    this.reportHtmlUrl = reportHtmlUrl;
    return this;
  }

  public String getReportPdfUrl() {
    return reportPdfUrl;
  }

  public SonatypeSecurityReport setReportPdfUrl(String reportPdfUrl) {
    this.reportPdfUrl = reportPdfUrl;
    return this;
  }

  public String getReportDataUrl() {
    return reportDataUrl;
  }

  public SonatypeSecurityReport setReportDataUrl(String reportDataUrl) {
    this.reportDataUrl = reportDataUrl;
    return this;
  }

  public String getPolicyAction() {
    return policyAction;
  }

  public SonatypeSecurityReport setPolicyAction(String policyAction) {
    this.policyAction = policyAction;
    return this;
  }

  public PolicyEvaluationResult getPolicyEvaluationResult() {
    return policyEvaluationResult;
  }

  public SonatypeSecurityReport setPolicyEvaluationResult(PolicyEvaluationResult policyEvaluationResult) {
    this.policyEvaluationResult = policyEvaluationResult;
    return this;
  }
}
