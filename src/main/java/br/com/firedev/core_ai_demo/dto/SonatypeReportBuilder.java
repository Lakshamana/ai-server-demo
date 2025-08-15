package br.com.firedev.core_ai_demo.dto;

// Builder utility class for easy object creation
public class SonatypeReportBuilder {

  public static SonatypeSecurityReport createReport() {
    return new SonatypeSecurityReport();
  }

  public static PolicyEvaluationResult createPolicyResult() {
    return new PolicyEvaluationResult();
  }

  public static Alert createAlert() {
    return new Alert();
  }

  public static Trigger createTrigger() {
    return new Trigger();
  }

  public static ComponentFact createComponentFact() {
    return new ComponentFact();
  }

  public static ComponentIdentifier createComponentIdentifier() {
    return new ComponentIdentifier();
  }

  public static MavenCoordinates createMavenCoordinates() {
    return new MavenCoordinates();
  }

  public static ConstraintFact createConstraintFact() {
    return new ConstraintFact();
  }

  public static ConditionFact createConditionFact() {
    return new ConditionFact();
  }

  public static Reference createReference() {
    return new Reference();
  }

  public static DisplayName createDisplayName() {
    return new DisplayName();
  }

  public static DisplayNamePart createDisplayNamePart(String field, String value) {
    return new DisplayNamePart(field, value);
  }

  // Example usage method
  public static SonatypeSecurityReport buildSampleReport() {
    return createReport()
        .setApplicationId("APM0005836_accessmanagement-api")
        .setScanId("bf08cbc1adf046fcada783fd318b689c")
        .setPolicyAction("None")
        .setPolicyEvaluationResult(
            createPolicyResult()
                .setTotalComponentCount(147)
                .setAffectedComponentCount(5)
                .setModeratePolicyViolationCount(2)
                .addAlert(
                    createAlert().setTrigger(
                        createTrigger()
                            .setPolicyName("Security-Low")
                            .setThreatLevel(1)
                            .addComponentFact(
                                createComponentFact()
                                    .setHash("6b29d341cc5e2b9c1714")
                                    .setComponentIdentifier(
                                        createComponentIdentifier()
                                            .setFormat("maven")
                                            .setCoordinates(
                                                createMavenCoordinates()
                                                    .setGroupId("org.springframework.ldap")
                                                    .setArtifactId("spring-ldap-core")
                                                    .setVersion("2.4.1")))))));
  }
}