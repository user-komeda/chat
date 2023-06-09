package com.example.chat.apprication.Advice;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.chat.apprication.Exception.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    ApiError re = new ApiError(status, ex.getMessage());
    return super.handleExceptionInternal(ex, re, headers, status, request);
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException exception,
      WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, exception.getMessage());
    return handleExceptionInternal(exception, apiError, new HttpHeaders(), apiError.getStatus(),
        request);
  }
}
