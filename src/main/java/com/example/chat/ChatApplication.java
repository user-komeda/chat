package com.example.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/** chatApplication. */
@EnableAutoConfiguration
@ComponentScan
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
