package br.com.firedev.core_ai_demo.context;

import java.util.List;

import br.com.firedev.core_ai_demo.dto.CodeChunk;

public class QueryContextHolder {
  private static final ThreadLocal<List<CodeChunk>> threadLocalDocs = new ThreadLocal<>();

  public static void setContext(List<CodeChunk> docs) {
    threadLocalDocs.set(docs);
  }

  public static List<CodeChunk> getContext() {
    return threadLocalDocs.get();
  }

  public static void clear() {
    threadLocalDocs.remove();
  }
}
