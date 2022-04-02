package com.example.chat;

import lombok.experimental.UtilityClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** chatApplication. */
@UtilityClass
@SpringBootApplication
public final class ChatApplication {

  /**
   * mainメソッド.
   *
   * @param args args
   */
  public static void main(final String[] args) {
    SpringApplication.run(ChatApplication.class, args);
  }
}
