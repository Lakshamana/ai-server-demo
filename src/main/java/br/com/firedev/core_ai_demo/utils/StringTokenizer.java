package br.com.firedev.core_ai_demo.utils;

import java.util.Arrays;
import java.util.stream.Stream;

import org.slf4j.Logger;

import java.util.regex.Pattern;

public class StringTokenizer {

  /**
   * Tokenizes a string into a stream where each token is preceded by "_"
   * and line breaks are preserved as separate tokens.
   *
   * @param input the string to tokenize
   * @return a Stream of tokens with "_" prefix
   */
  public static Stream<String> tokenizeWithUnderscorePrefix(String input, Logger logger) {
    if (input == null || input.isEmpty()) {
      return Stream.empty();
    }

    Stream.Builder<String> builder = Stream.builder();
    StringBuilder currentToken = new StringBuilder();
    boolean inCodeBlock = false;

    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);

      // Check if we're entering or exiting a code block
      if (i <= input.length() - 3 && input.substring(i, i + 3).equals("```")) {
        // Flush any current token
        if (currentToken.length() > 0) {
          logger.info("Token: " + currentToken.toString());
          builder.add("_" + currentToken.toString());
          currentToken.setLength(0);
        }

        // Toggle code block state
        inCodeBlock = !inCodeBlock;

        // Add the code block marker as a token
        builder.add("_```");
        i += 2; // Skip the next two backticks
        continue;
      }

      if (Character.isWhitespace(c)) {
        if (inCodeBlock) {
          // Inside code blocks, preserve all whitespace as part of tokens
          if (c == ' ') {
            currentToken.append(' ');
          } else if (c == '\n') {
            // End current token and add newline
            if (currentToken.length() > 0) {
              logger.info("Token: " + currentToken.toString());
              builder.add("_" + currentToken.toString());
              currentToken.setLength(0);
            }
            builder.add("_\n");
          } else if (c == '\r' && i + 1 < input.length() && input.charAt(i + 1) == '\n') {
            // Handle Windows line endings
            if (currentToken.length() > 0) {
              logger.info("Token: " + currentToken.toString());
              builder.add("_" + currentToken.toString());
              currentToken.setLength(0);
            }
            builder.add("_\n");
            i++; // Skip the \n
          } else {
            // Other whitespace (tabs, etc.) in code blocks
            currentToken.append(c);
          }
        } else {
          // Outside code blocks, normal tokenization
          if (currentToken.length() > 0) {
            logger.info("Token: " + currentToken.toString());
            builder.add("_" + currentToken.toString() + c);
            currentToken.setLength(0);
          }

          if (c == '\n') {
            builder.add("_\n");
          } else if (c == '\r' && i + 1 < input.length() && input.charAt(i + 1) == '\n') {
            builder.add("_\n");
            i++; // Skip the \n
          } if (c == ' ') {
            currentToken.append(' ');
          }
          // Other whitespace is ignored outside code blocks
        }
      } else {
        // Non-whitespace characters are always accumulated
        currentToken.append(c);
      }
    }

    // Don't forget the last token
    if (currentToken.length() > 0) {
      logger.info("Token: " + currentToken.toString());
      builder.add("_" + currentToken.toString());
    }

    return builder.build();
  }
}
