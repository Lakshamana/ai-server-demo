package br.com.firedev.core_ai_demo.utils;

import br.com.firedev.core_ai_demo.dto.Alert;
import br.com.firedev.core_ai_demo.dto.ComponentFact;
import br.com.firedev.core_ai_demo.dto.ComponentIdentifier;
import br.com.firedev.core_ai_demo.dto.ConditionFact;
import br.com.firedev.core_ai_demo.dto.ConstraintFact;
import br.com.firedev.core_ai_demo.dto.DisplayName;
import br.com.firedev.core_ai_demo.dto.DisplayNamePart;
import br.com.firedev.core_ai_demo.dto.MavenCoordinates;
import br.com.firedev.core_ai_demo.dto.PolicyEvaluationResult;
import br.com.firedev.core_ai_demo.dto.Reference;
import br.com.firedev.core_ai_demo.dto.SonatypeSecurityReport;
import br.com.firedev.core_ai_demo.dto.Trigger;

public class SonatypeResponseFactory {

  public static SonatypeSecurityReport build() {
    return new SonatypeSecurityReport()
        .setApplicationId("APM0005836_accessmanagement-api")
        .setScanId("bf08cbc1adf046fcada783fd318b689c")
        .setReportHtmlUrl(
            "https://sonatype.fiserv.one/ui/links/application/APM0005836_accessmanagement-api/report/bf08cbc1adf046fcada783fd318b689c")
        .setReportPdfUrl(
            "https://sonatype.fiserv.one/ui/links/application/APM0005836_accessmanagement-api/report/bf08cbc1adf046fcada783fd318b689c/pdf")
        .setReportDataUrl(
            "https://sonatype.fiserv.one/api/v2/applications/APM0005836_accessmanagement-api/reports/bf08cbc1adf046fcada783fd318b689c/raw")
        .setPolicyAction("None")
        .setPolicyEvaluationResult(createPolicyEvaluationResult());
  }

  private static PolicyEvaluationResult createPolicyEvaluationResult() {
    return new PolicyEvaluationResult()
        .setAffectedComponentCount(5)
        .setCriticalComponentCount(0)
        .setSevereComponentCount(0)
        .setModerateComponentCount(2)
        .setCriticalPolicyViolationCount(0)
        .setSeverePolicyViolationCount(0)
        .setModeratePolicyViolationCount(2)
        .setLegacyViolationCount(0)
        .setTotalComponentCount(147)
        .setCriticalSastPolicyViolationCount(0)
        .setSevereSastPolicyViolationCount(0)
        .setModerateSastPolicyViolationCount(0)
        .setTotalSastFindingCount(0)
        .setGrandfatheredPolicyViolationCount(0)
        .addAlert(createSecurityLowAlert())
        .addAlert(createSecurityMediumAlert())
        .addAlert(createComponentUnknownAlert())
        .addAlert(createLicenseWeakCopyleftAlert())
        .addAlert(createQualityHygieneAlert());
  }

  // Alert 1: Security-Low (Spring LDAP Core)
  private static Alert createSecurityLowAlert() {
    return new Alert()
        .setTrigger(
            new Trigger()
                .setPolicyId("0edab051fc73413f9422bf37c0b27a7d")
                .setPolicyName("Security-Low")
                .setThreatLevel(1)
                .setPolicyViolationId("cd8e3a38e12e4eb6a034467b0d219649")
                .addComponentFact(
                    new ComponentFact()
                        .setComponentIdentifier(
                            new ComponentIdentifier()
                                .setFormat("maven")
                                .setCoordinates(
                                    new MavenCoordinates()
                                        .setArtifactId("spring-ldap-core")
                                        .setClassifier("")
                                        .setExtension("jar")
                                        .setGroupId("org.springframework.ldap")
                                        .setVersion("2.4.1")))
                        .setHash("6b29d341cc5e2b9c1714")
                        .addConstraintFact(
                            new ConstraintFact()
                                .setConstraintId("a5ea06062bf64aeab5510bd12e2d21ba")
                                .setConstraintName("Low risk CVSS score")
                                .setOperatorName("AND")
                                .addConditionFact(
                                    new ConditionFact()
                                        .setConditionTypeId("SecurityVulnerabilitySeverity")
                                        .setConditionIndex(0)
                                        .setSummary("Security Vulnerability Severity >= 0")
                                        .setReason(
                                            "Found security vulnerability CVE-2024-38829 with severity >= 0 (severity = 2.3)")
                                        .setReference(
                                            new Reference()
                                                .setValue("CVE-2024-38829")
                                                .setType("SECURITY_VULNERABILITY_REFID"))))
                        .setDisplayName(
                            new DisplayName()
                                .setName("spring-ldap-core")
                                .addPart(new DisplayNamePart().setField("Group").setValue("org.springframework.ldap"))
                                .addPart(new DisplayNamePart().setValue(" : "))
                                .addPart(new DisplayNamePart().setField("Artifact").setValue("spring-ldap-core"))
                                .addPart(new DisplayNamePart().setValue(" : "))
                                .addPart(new DisplayNamePart().setField("Version").setValue("2.4.1")))));
  }

  // Alert 2: Security-Medium (Gson)
  private static Alert createSecurityMediumAlert() {
    return new Alert()
        .setTrigger(
            new Trigger()
                .setPolicyId("3324dd5084cf4b00bb96b6d3edeb6736")
                .setPolicyName("Security-Medium")
                .setThreatLevel(3)
                .setPolicyViolationId("f1212beb902e4985abcce231a0124479")
                .addComponentFact(
                    new ComponentFact()
                        .setComponentIdentifier(
                            new ComponentIdentifier()
                                .setFormat("maven")
                                .setCoordinates(
                                    new MavenCoordinates()
                                        .setArtifactId("gson")
                                        .setClassifier("")
                                        .setExtension("jar")
                                        .setGroupId("com.google.code.gson")
                                        .setVersion("2.11.0")))
                        .setHash("527175ca6d81050b53bd")
                        .addConstraintFact(
                            new ConstraintFact()
                                .setConstraintId("b97e14796c0c464b8f5d1ff00d94f6d0")
                                .setConstraintName("Medium risk CVSS score")
                                .setOperatorName("AND")
                                .addConditionFact(
                                    new ConditionFact()
                                        .setConditionTypeId("SecurityVulnerabilitySeverity")
                                        .setConditionIndex(0)
                                        .setSummary("Security Vulnerability Severity >= 4")
                                        .setReason(
                                            "Found security vulnerability sonatype-2025-000535 with severity >= 4 (severity = 6.9)")
                                        .setReference(
                                            new Reference()
                                                .setValue("sonatype-2025-000535")
                                                .setType("SECURITY_VULNERABILITY_REFID"))))
                        .setDisplayName(
                            new DisplayName()
                                .setName("gson")
                                .addPart(new DisplayNamePart().setField("Group").setValue("com.google.code.gson"))
                                .addPart(new DisplayNamePart().setValue(" : "))
                                .addPart(new DisplayNamePart().setField("Artifact").setValue("gson"))
                                .addPart(new DisplayNamePart().setValue(" : "))
                                .addPart(new DisplayNamePart().setField("Version").setValue("2.11.0")))));
  }

  // Alert 3: Component-Unknown
  private static Alert createComponentUnknownAlert() {
    return new Alert()
        .setTrigger(
            new Trigger()
                .setPolicyId("3024bea7369b4ab6af8444e92e3b7000")
                .setPolicyName("Component-Unknown")
                .setThreatLevel(0)
                .setPolicyViolationId("e10914742f3b40cf9271d8bec01add7c")
                .addComponentFact(
                    new ComponentFact()
                        .setComponentIdentifier(null) // Unknown component has null identifier
                        .setHash("1d631096d6d1a5213e42")
                        .addConstraintFact(
                            new ConstraintFact()
                                .setConstraintId("ea9cdcd1cce044dfb3f8805c49aa12e2")
                                .setConstraintName("Unknown 3rd party component")
                                .setOperatorName("AND")
                                .addConditionFact(
                                    new ConditionFact()
                                        .setConditionTypeId("MatchState")
                                        .setConditionIndex(0)
                                        .setSummary("Match State is unknown")
                                        .setReason("Match state was 'Unknown'")
                                        .setReference(null) // No reference for unknown components
                                ))
                        .setDisplayName(null) // Unknown component has null display name
                ));
  }

  // Alert 4: License - Weak Copyleft (Javassist)
  private static Alert createLicenseWeakCopyleftAlert() {
    return new Alert()
        .setTrigger(
            new Trigger()
                .setPolicyId("5e6a21e0fb884b1abc7f409ce7f688c3")
                .setPolicyName("License - Weak Copyleft")
                .setThreatLevel(0)
                .setPolicyViolationId("59282081f44a4919b8c6fe7b24884692")
                .addComponentFact(
                    new ComponentFact()
                        .setComponentIdentifier(
                            new ComponentIdentifier()
                                .setFormat("maven")
                                .setCoordinates(
                                    new MavenCoordinates()
                                        .setArtifactId("javassist")
                                        .setClassifier("")
                                        .setExtension("jar")
                                        .setGroupId("org.javassist")
                                        .setVersion("3.30.2-GA")))
                        .setHash("284580b5e42dfa1b8267")
                        .addConstraintFact(
                            new ConstraintFact()
                                .setConstraintId("b36c5c1b87ee4373891efa72b2b3530b")
                                .setConstraintName("License")
                                .setOperatorName("OR")
                                .addConditionFact(
                                    new ConditionFact()
                                        .setConditionTypeId("License Threat Group")
                                        .setConditionIndex(0)
                                        .setSummary("License Threat Group is 'Weak Copyleft'")
                                        .setReason(
                                            "Found licenses in the 'Weak Copyleft' license threat group ('LGPL-2.1', 'LGPL-3.0', 'MPL-1.1')")
                                        .setReference(null) // License violations typically don't have references
                                ))
                        .setDisplayName(
                            new DisplayName()
                                .setName("javassist")
                                .addPart(new DisplayNamePart().setField("Group").setValue("org.javassist"))
                                .addPart(new DisplayNamePart().setValue(" : "))
                                .addPart(new DisplayNamePart().setField("Artifact").setValue("javassist"))
                                .addPart(new DisplayNamePart().setValue(" : "))
                                .addPart(new DisplayNamePart().setField("Version").setValue("3.30.2-GA")))));
  }

  // Alert 5: Quality-HygieneWarning (JAXB Core)
  private static Alert createQualityHygieneAlert() {
    return new Alert()
        .setTrigger(
            new Trigger()
                .setPolicyId("eebf8456d8bc49859642d3eee7c66e59")
                .setPolicyName("Quality-HygieneWarning")
                .setThreatLevel(1)
                .setPolicyViolationId("d6880438e4ed41a5920780c1e8b0b33f")
                .addComponentFact(
                    new ComponentFact()
                        .setComponentIdentifier(
                            new ComponentIdentifier()
                                .setFormat("maven")
                                .setCoordinates(
                                    new MavenCoordinates()
                                        .setArtifactId("jaxb-core")
                                        .setClassifier("")
                                        .setExtension("jar")
                                        .setGroupId("org.glassfish.jaxb")
                                        .setVersion("4.0.5")))
                        .setHash("007b4b11ea5542eea4ad")
                        .addConstraintFact(
                            new ConstraintFact()
                                .setConstraintId("c498aaadc68349deb7960f63ba17018c")
                                .setConstraintName("Quality-HygieneWarning")
                                .setOperatorName("OR")
                                .addConditionFact(
                                    new ConditionFact()
                                        .setConditionTypeId("HygieneRating")
                                        .setConditionIndex(0)
                                        .setSummary("Hygiene Rating is Laggard")
                                        .setReason("Hygiene Rating was Laggard")
                                        .setReference(null) // Quality issues typically don't have external references
                                ))
                        .setDisplayName(
                            new DisplayName()
                                .setName("jaxb-core")
                                .addPart(new DisplayNamePart().setField("Group").setValue("org.glassfish.jaxb"))
                                .addPart(new DisplayNamePart().setValue(" : "))
                                .addPart(new DisplayNamePart().setField("Artifact").setValue("jaxb-core"))
                                .addPart(new DisplayNamePart().setValue(" : "))
                                .addPart(new DisplayNamePart().setField("Version").setValue("4.0.5")))));
  }
}
