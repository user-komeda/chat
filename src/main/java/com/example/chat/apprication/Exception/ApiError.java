package com.example.chat.apprication.Exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ApiError {

  private HttpStatus status;
  private String message;
}
