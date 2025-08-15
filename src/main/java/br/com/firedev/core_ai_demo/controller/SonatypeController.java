package br.com.firedev.core_ai_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.firedev.core_ai_demo.dto.SonatypeFetchNextRecommendVersionRequest;
import br.com.firedev.core_ai_demo.dto.SonatypeRecommendedVersion;
import br.com.firedev.core_ai_demo.dto.SonatypeSecurityReport;
import br.com.firedev.core_ai_demo.utils.SonatypeResponseFactory;

@RestController()
@RequestMapping("/api/sonatype")
public class SonatypeController {

  private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  public SonatypeSecurityReport scan(@RequestParam("file") MultipartFile file, @RequestParam("dto") String dto) {
    if (file == null || file.isEmpty() || dto == null || dto.isEmpty()) {
      throw new IllegalArgumentException("File and DTO are required");
    }

    logger.info(String.format("Received file=MultipartFile[name=%s, size=%d] and dto=\"%s\"",
        file.getOriginalFilename(), file.getSize(), dto));

    return SonatypeResponseFactory.build();
  }

  @PostMapping("/version")
  public SonatypeRecommendedVersion fetchNextRecommendedVersion(
      @RequestBody() SonatypeFetchNextRecommendVersionRequest dto) {
      logger.info("Received next recommended version: {}", dto);
      return new SonatypeRecommendedVersion("1.0.0");
  }
}
