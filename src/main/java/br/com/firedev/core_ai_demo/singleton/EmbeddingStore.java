package br.com.firedev.core_ai_demo.singleton;

import dev.langchain4j.store.embedding.chroma.ChromaEmbeddingStore;

public class EmbeddingStore {
    public static final ChromaEmbeddingStore embeddingStore = ChromaEmbeddingStore.builder()
        .baseUrl("http://localhost:8000")
        .collectionName("default")
        .build();
}
