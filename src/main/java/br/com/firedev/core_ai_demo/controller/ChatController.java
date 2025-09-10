package br.com.firedev.core_ai_demo.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.firedev.core_ai_demo.dto.ChatRequest;
import br.com.firedev.core_ai_demo.dto.ChatResponse;
import br.com.firedev.core_ai_demo.dto.CreateEntityInput;
import br.com.firedev.core_ai_demo.dto.EmbeddingResult;
import br.com.firedev.core_ai_demo.dto.ExplainCodeInput;
import br.com.firedev.core_ai_demo.dto.SingleEmbeddingRequest;
import br.com.firedev.core_ai_demo.dto.StreamingEmbeddingRequest;
import br.com.firedev.core_ai_demo.service.AssistantService;
import br.com.firedev.core_ai_demo.service.EmbeddingService;
import br.com.firedev.core_ai_demo.utils.EventUtils;
import br.com.firedev.core_ai_demo.utils.StringTokenizer;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatController {

  private Logger logger;

  @Autowired
  private AssistantService assistantService;

  @Autowired
  private EmbeddingService embeddingService;

  public ChatController() {
    this.logger = LoggerFactory.getLogger(this.getClass().getName());
  }

  @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<String>> chat(@RequestBody ChatRequest request) {

    logger.info("Chat id: " + request.getChatId());
    logger.info("Request: " + request.toString());

    return assistantService.chat(request);
  }

  @PostMapping(value = "/stream-mock", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<String>> chatMock(@RequestBody ChatRequest request) {
    logger.info("Chat id: " + request.getChatId());
    logger.info("Request: " + request.toString());

    String mockString = """
        ## Mock Response
        This is a sample response to the user input. ```javascript
        function exemploCorrecao() {
            console.log('teste')
            if (true) {
                console.log('true')
            }
        } ```

        More text here...
        ```javascript
        function exemploCorrecao2() {
            console.log('teste')
        }```## Lets add a second code block

        Now a third code block
        ```javascript
        function exemploCorrecao3() {
            console.log('teste')
        }
        ``` any text right after the code block without breaking line, but with spacing

        ### Now a 4th code block
        ```javascript
        function exemploCorrecao4() {
            console.log('teste')
        }```
        any text right after the code block with proper spacing, check `exemploCorrecao4` and `exemploCorrecao3`

        more text here...
        """;

    return Flux.create(sink -> {
      sink.next(ServerSentEvent.<String>builder()
          .event("chatId")
          .id(UUID.randomUUID().toString())
          .data(String.valueOf(1L))
          .build());

      StringTokenizer.tokenizeWithUnderscorePrefix(mockString, logger).forEach(token -> {
        sink.next(ServerSentEvent.<String>builder()
            .event("token")
            .id(UUID.randomUUID().toString())
            .data(token)
            .build());

        logger.info(token);
      });

      sink.complete();
    });
  }

  @PostMapping(value = "/embed-prompt", consumes = MediaType.APPLICATION_JSON_VALUE)
  public EmbeddingResult embedPrompt(@RequestBody SingleEmbeddingRequest request) {
    logger.info("Received single embedding request: {}", request);

    return embeddingService.embedPrompt(request.getPrompt());
  }

  @PostMapping(value = "/setup", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ServerSentEvent<EmbeddingResult>> streamEmbeddings(
      @RequestBody StreamingEmbeddingRequest request) {

    logger.info("Received stream request: {}", request);

    return embeddingService.processChunksStreamWithProgress(request.getProjectId(), request.getChunks())
        .map(result -> {
          String eventType = EventUtils.determineEventType(result.getStatus());

          return ServerSentEvent.<EmbeddingResult>builder()
              .id(result.getId())
              .event(eventType)
              .data(result)
              .comment(EventUtils.createProgressComment(result))
              .build();
        })
        .doOnError(error -> System.err.println("Streaming error:" + error.getMessage()));
  }

  @PostMapping("/explain-code")
  public ChatResponse explainCode(@RequestBody ExplainCodeInput request) {
    String response = """
        ## Explain Code
        This is a sample response to explain the code.

        ```javascript
        function exemploCorrecao() {
            console.log('teste')
        }
        ```""";

    return new ChatResponse(response);
  }

  @PostMapping("/code-review")
  public ChatResponse codeReview(@RequestBody ExplainCodeInput request) {
    String response = """
        ## Review Code
        This is a sample response to explain the code. ```javascript
        function exemploCorrecao() {
            console.log('teste')
        }
        ```""";

    return new ChatResponse(response);
  }

  @PostMapping("/create-entity")
  public ChatResponse createEntity(@RequestBody CreateEntityInput request) {
    String response = String.format("""
        ## Create entity
        This is a sample response to create the entity code for client type %s.

        ```typescript
        export class %s {
          constructor(%s) {
            // code here
          }
        }
        ```""", request.getClient(), request.getEntityName(), request.getAttributes());

    return new ChatResponse(response);
  }

  @PostMapping("/generate-unit-tests")
  public ChatResponse createTestSuite(@RequestBody CreateEntityInput request) {
    String response = String.format("""
        ## Unit tests
        This is a sample response to create the entity code for client type %s.
        ```typescript
        export class %s {
          constructor(%s) {
            // code here
          }
        }
        ```""", request.getClient(), request.getEntityName(), request.getAttributes());

    return new ChatResponse(response);
  }

  @PostMapping("/generate-docs")
  public ChatResponse generateDocs(@RequestBody CreateEntityInput request) {
    String response = String.format("""
        ## Unit tests
        This is a sample response to create the entity code for client type %s.
        ```typescript
        export class %s {
          constructor(%s) {
            // code here
          }
        }
        ```""", request.getClient(), request.getEntityName(), request.getAttributes());

    return new ChatResponse(response);
  }
}
