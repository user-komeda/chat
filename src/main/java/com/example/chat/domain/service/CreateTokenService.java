package com.example.chat.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.chat.domain.object.RefreshToken;
import com.example.chat.domain.repository.RefreshTokenRepository;
import com.example.chat.domain.repository.UserRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * CreateTokenService.
 */
@NoArgsConstructor
@Service
public class CreateTokenService {

  /**
   * RefreshTokenRepository.
   */
  @Autowired
  private transient RefreshTokenRepository refreshTokenRepository;

  /**
   * UserRepository.
   */
  @Autowired
  private transient UserRepository userRepository;

  /**
   * refreshToken作成.
   *
   * @param ex 認証情報
   * @return refreshToken
   */
  public String crateRefreshToken(Authentication ex) {

    //有効期限14日
    String refreshToken = JWT.create().withIssuer("com.example.chat").withIssuedAt(Instant.now())
        .withNotBefore(Instant.now()).withExpiresAt(Instant.now().plusSeconds(60 * 60 * 24 * 14))
        .withClaim("username", ex.getName()).sign(Algorithm.HMAC256("secret"));

    Long userId = userRepository.findByEmail(ex.getName()).getId();

    return refreshTokenRepository.save(new RefreshToken(null, refreshToken, userId,
        LocalDate.ofInstant(Instant.now(), ZoneOffset.UTC))).getRefreshToken();

  }

  /**
   * refreshToken作成.
   *
   * @param ex 認証情報
   * @return refreshToken
   */
  public String createToken(Authentication ex) {
    //有効期限30分
    return JWT.create().withIssuer("com.example.chat").withIssuedAt(Instant.now())
        .withNotBefore(Instant.now()).withExpiresAt(Instant.now().plusSeconds(60 * 30))
        .withClaim("username", ex.getName()).sign(Algorithm.HMAC256("secret"));
  }

  /**
   * @param cookies
   * @return
   */
  public String getRefreshTokenCookie(Cookie[] cookies) {
    return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("refreshToken"))
        .findFirst().orElseThrow(RuntimeException::new).getValue();
  }

  public boolean verifyRefreshToken(String refreshToken, String email) {
    Long userId = userRepository.findByEmail(email).getId();
    RefreshToken findedRefreshToken = refreshTokenRepository.findByUserId(userId);
    return refreshToken.equals(findedRefreshToken.getRefreshToken())
        && findedRefreshToken.isExpirationDate();
  }


}
