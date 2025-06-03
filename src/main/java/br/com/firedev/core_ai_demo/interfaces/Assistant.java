package br.com.firedev.core_ai_demo.interfaces;

import dev.langchain4j.service.TokenStream;

public interface Assistant {
  TokenStream chat(String userMessage);
}
