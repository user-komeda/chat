package com.example.application.advice;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.domain.exception.ApiError;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ExceptionHandlerAdvice.
 */
@RestControllerAdvice
@NoArgsConstructor
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final Object body,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    final ApiError apiError = new ApiError(status, ex.getMessage());
    return super.handleExceptionInternal(ex, apiError, headers, status, request);
  }

  /**
   * 例外補足.
   *
   * @param exception exception
   * @param request   request
   * @return 補足結果
   */
  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<Object> handleTokenExpiredException(final TokenExpiredException exception,
      final WebRequest request) {
    final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, exception.getMessage());
    return handleExceptionInternal(exception, apiError, new HttpHeaders(), apiError.getStatus(),
        request);
  }
}
