package com.example.chat.apprication.filter;

import com.example.chat.apprication.resource.SignupBody;
import com.example.chat.domain.service.CreateTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
   * CreateTokenService.
   */
  private final transient CreateTokenService createTokenService;


  /**
   * constructor.
   *
   * @param authenticationManager authenticationManager.
   */
  public UsernamePasswordAuthenticationFilterImpl(
      final AuthenticationManager authenticationManager, final ApplicationContext context) {
    super();
    this.authenticationManager = authenticationManager;
    this.createTokenService = context.getBean(CreateTokenService.class);
    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/signup", "POST"));
    setUsernameParameter("email");
    setPasswordParameter("password");
    this.setAuthenticationSuccessHandler((req, res, ex) -> {
      //有効期限30分
      final String token = createTokenService.createToken(ex);
      //有効期限14日
      final String refreshToken = createTokenService.crateRefreshToken(ex);
      final ResponseCookie cookie = ResponseCookie.from("refreshToken",
              refreshToken)
          .httpOnly(true).path("/")
          .build();
      res.setHeader("X-AUTH-TOKEN", token);
      res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
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
