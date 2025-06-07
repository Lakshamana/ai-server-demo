package br.com.firedev.core_ai_demo.utils;

import br.com.firedev.core_ai_demo.dto.EmbeddingResult;

public class EventUtils {
  public static String determineEventType(String status) {
    return switch (status) {
      case "processing" -> "chunk-processing";
      case "completed" -> "chunk-completed";
      case "error" -> "chunk-error";
      case "batch_complete" -> "batch-complete";
      default -> "unknown";
    };
  }

  public static String createProgressComment(EmbeddingResult result) {
    if (result.getTotalChunks() > 0) {
      double progress = (double) result.getProcessedChunks() / result.getTotalChunks() * 100;
      return String.format("Progress: %.1f%% (%d/%d)", progress,
          result.getProcessedChunks(), result.getTotalChunks());
    }

    return "";
  }
}
