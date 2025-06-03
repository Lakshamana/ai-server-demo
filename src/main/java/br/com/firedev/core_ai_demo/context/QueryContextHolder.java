package br.com.firedev.core_ai_demo.context;

import java.util.List;

import dev.langchain4j.data.segment.TextSegment;

public class QueryContextHolder {
  private static final ThreadLocal<List<TextSegment>> threadLocalDocs = new ThreadLocal<>();

  public static void setContext(List<TextSegment> docs) {
    threadLocalDocs.set(docs);
  }

  public static List<TextSegment> getContext() {
    return threadLocalDocs.get();
  }

  public static void clear() {
    threadLocalDocs.remove();
  }
}
