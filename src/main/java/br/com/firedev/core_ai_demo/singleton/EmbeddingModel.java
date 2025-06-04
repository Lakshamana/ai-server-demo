package br.com.firedev.core_ai_demo.singleton;

import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModelFactory;

public class EmbeddingModel {
  public static final dev.langchain4j.model.embedding.EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModelFactory()
      .create();
}
