package br.com.firedev.core_ai_demo.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.firedev.core_ai_demo.dto.ChatRequest;
import br.com.firedev.core_ai_demo.service.AssistantService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

  private Logger logger;

  @Autowired
  private AssistantService assistantService;

  public ChatController() {
    this.logger = Logger.getLogger(ChatController.class.getName());
  }

  @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<String>> chat(@RequestBody ChatRequest request) {

    logger.info("Chat id: " + request.getChatId());
    logger.info("Request: " + request.toString());

    return assistantService.chat(request);
  }
}
