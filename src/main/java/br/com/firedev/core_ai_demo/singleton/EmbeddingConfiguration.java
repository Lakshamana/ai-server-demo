package br.com.firedev.core_ai_demo.singleton;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModelFactory;
import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;

public class EmbeddingConfiguration {
  public static final EmbeddingModel EMBEDDING_MODEL = new AllMiniLmL6V2EmbeddingModelFactory()
      .create();

  public static final ChromaEmbeddingStore EMBEDDING_STORE = ChromaEmbeddingStore.builder()
      .baseUrl("http://localhost:8000")
      .collectionName("default")
      .build();
}
