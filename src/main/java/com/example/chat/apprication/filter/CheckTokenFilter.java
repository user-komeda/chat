package com.example.chat.apprication.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * CheckTokenFilter.
 */
@NoArgsConstructor
public class CheckTokenFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain filterChain) throws ServletException, IOException {
    final String token = request.getHeader("X-AUTH-TOKEN");

    //　トークンが無い場合は何もせずセッションの状態を維持しない
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }

    final DecodedJWT jwt = JWT.require(Algorithm.HMAC256("secret")).build().verify(token);
    // usernameの取得
    final String email = jwt.getClaim("email").toString();

    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>()));
    filterChain.doFilter(request, response);
  }
}
