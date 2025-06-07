package br.com.firedev.core_ai_demo.singleton;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModelFactory;

public class EmbeddingConfiguration {
  public static final EmbeddingModel EMBEDDING_MODEL = new AllMiniLmL6V2EmbeddingModelFactory()
      .create();
}
