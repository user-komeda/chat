package com.example.chat.apprication.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.chat.apprication.exception.TokenNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * CheckTokenFilter.
 */
@AllArgsConstructor
public class CheckTokenFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {
    final String token = request.getHeader("X-AUTH-TOKEN");
    //　トークンが無い場合は何もせずセッションの状態を維持しない
    if (!StringUtils.hasText(token)) {
      throw new TokenNotFoundException("");
    }
    final DecodedJWT jwt = JWT.require(Algorithm.HMAC256("secret")).build().verify(token);
    final String email = Objects.requireNonNull(jwt).getClaim("email").toString();
    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>()));
    // usernameの取得
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(final HttpServletRequest request) throws ServletException {
    return "/refreshToken/".equals(request.getServletPath()) || "/websocket"
        .equals(request.getServletPath());
  }
}

