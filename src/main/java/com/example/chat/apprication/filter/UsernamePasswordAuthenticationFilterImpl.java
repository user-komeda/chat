package com.example.chat.apprication.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.chat.apprication.resource.SignupBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * UsernamePasswordAuthenticationFilterImpl.
 */
public class UsernamePasswordAuthenticationFilterImpl extends UsernamePasswordAuthenticationFilter {

  /**
   * authenticationManager.
   */
  private final transient AuthenticationManager authenticationManager;

  /**
   * constructor.
   *
   * @param authenticationManager authenticationManager.
   */
  public UsernamePasswordAuthenticationFilterImpl(
      final AuthenticationManager authenticationManager) {
    super();
    this.authenticationManager = authenticationManager;
    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/signup", "POST"));
    setUsernameParameter("email");
    setPasswordParameter("password");

    this.setAuthenticationSuccessHandler((req, res, ex) -> {
      final String token = JWT.create().withIssuer("com.example.chat")
          .withClaim("username", ex.getName())
          .sign(Algorithm.HMAC256("secret"));
      res.setHeader("X-AUTH-TOKEN", token);
      res.setHeader("Access-Control-Expose-Headers", "X-AUTH-TOKEN");
      res.setStatus(200);
    });

    this.setAuthenticationFailureHandler((req, res, ex) -> {
      res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    });

  }

  @Override
  public Authentication attemptAuthentication(final HttpServletRequest request,
      final HttpServletResponse response) {
    try {
      // あとで作成するLoginFormクラスを、リクエストのパラメータとマッピングして作成する
      final SignupBody body = new ObjectMapper().readValue(request.getInputStream(),
          SignupBody.class);
      // 作成したLoginFormクラスの内容でログインの実行をする
      return this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword())
      );
    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
