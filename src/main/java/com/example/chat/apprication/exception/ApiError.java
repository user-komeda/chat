package com.example.chat.apprication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * ApiError.
 */
@AllArgsConstructor
@Data
public class ApiError {

  /**
   * httpステータス.
   */
  private HttpStatus status;

  /**
   * メッセージ.
   */
  private String message;
}
