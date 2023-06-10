package com.example.chat.apprication.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.chat.apprication.exception.TokenNotFoundException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * ExceptionHandlerFilter.
 */
@NoArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {

  /**
   * TOKEN_NOT_FOUNT_EXCEPTION_NAME.
   */
  private static final String TOKEN_NOT_FOUNT_EXCEPTION_NAME =
      "com.example.chat.apprication.exception.TokenNotFoundException";

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (TokenNotFoundException | JWTVerificationException exception) {
      switch (exception.getClass().getName()) {
        case TOKEN_NOT_FOUNT_EXCEPTION_NAME:
          response.sendError(HttpServletResponse.SC_FORBIDDEN, "tokenが存在しません");
          break;
        case "UserNotFoundException":
          response.sendError(HttpServletResponse.SC_FORBIDDEN, "");
          break;
        default:
          throw exception;
      }

    }
  }
}
