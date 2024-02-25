package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * chatApplication.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.example.application", "com.example.domain",
    "com.example.infratecture", "com.example.utils"})
@EnableJdbcRepositories("com.example.infratecture")
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