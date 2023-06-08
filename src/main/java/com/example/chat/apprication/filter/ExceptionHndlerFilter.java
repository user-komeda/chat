package com.example.chat.apprication.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExceptionHndlerFilter extends OncePerRequestFilter {

  private final String TOKEN_NOT_FOUNT_EXCEPTION_NAME = "com.example.chat.apprication.Exception.TokenNotFoundException";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (RuntimeException exception) {
      String name = "";
      switch (exception.getClass().getName()) {
        case TOKEN_NOT_FOUNT_EXCEPTION_NAME:
          System.out.println("aaa");
          response.sendError(HttpServletResponse.SC_FORBIDDEN, "tokenが存在しません");
        case "UserNotFoundException":
          break;
        default:
          System.out.println(exception.getClass().getName());
          throw exception;
      }

    }
  }
}
