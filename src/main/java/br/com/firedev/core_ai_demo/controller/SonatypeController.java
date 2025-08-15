package br.com.firedev.core_ai_demo.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.firedev.core_ai_demo.dto.SonatypeSecurityReport;
import br.com.firedev.core_ai_demo.utils.SonatypeResponseFactory;

@RestController()
@RequestMapping("/api/sonatype")
public class SonatypeController {

  private Logger logger = Logger.getLogger(SonatypeController.class.getName());

  @PostMapping("/scan")
  public SonatypeSecurityReport scan(@RequestParam("file") MultipartFile file, @RequestParam("dto") String dto) {
    if (file == null || file.isEmpty() || dto == null || dto.isEmpty()) {
      throw new IllegalArgumentException("File and DTO are required");
    }

    logger.info(String.format("Received file=MultipartFile[name=%s, size=%d] and dto=\"%s\"",
        file.getOriginalFilename(), file.getSize(), dto));

    return SonatypeResponseFactory.build();
  }
}
