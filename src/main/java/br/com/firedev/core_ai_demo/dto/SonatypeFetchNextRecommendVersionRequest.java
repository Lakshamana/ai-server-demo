package br.com.firedev.core_ai_demo.dto;

public class SonatypeFetchNextRecommendVersionRequest {

  private String applicationName;

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public String getComponentIdentifier() {
    return componentIdentifier;
  }

  public void setComponentIdentifier(String componentIdentifier) {
    this.componentIdentifier = componentIdentifier;
  }

  public String getScanId() {
    return scanId;
  }

  public void setScanId(String scanId) {
    this.scanId = scanId;
  }

  public String getDependencyType() {
    return dependencyType;
  }

  public void setDependencyType(String dependencyType) {
    this.dependencyType = dependencyType;
  }

  private String componentIdentifier;
  private String scanId;
  private String dependencyType;

  @Override
  public String toString() {
    return "SonatypeFetchNextRecommendVersionRequest [applicationName=" + applicationName + ", componentIdentifier="
        + componentIdentifier + ", scanId=" + scanId + ", dependencyType=" + dependencyType + "]";
  }
}
