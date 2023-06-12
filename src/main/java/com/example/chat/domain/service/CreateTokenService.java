package com.example.chat.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.chat.domain.object.RefreshToken;
import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.RefreshTokenRepository;
import com.example.chat.domain.repository.UserRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
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
   * ISSUER.
   */
  private static final String ISSUER = "com.example.chat";

  /**
   * USER_NAME_CLAIM.
   */
  private static final String USER_NAME_CLAIM = "username";

  /**
   * SECRET.
   */
  private static final String SECRET = "secret";

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
  public String crateRefreshToken(final Authentication ex) {

    //有効期限14日
    final String refreshToken = JWT.create().withIssuer(ISSUER)
        .withIssuedAt(Instant.now())
        .withNotBefore(Instant.now()).withExpiresAt(Instant.now().plusSeconds(60 * 60 * 24 * 14))
        .withClaim(USER_NAME_CLAIM, ex.getName()).sign(Algorithm.HMAC256(SECRET));

    final Long userId = userRepository.findByEmail(ex.getName()).getId();

    return refreshTokenRepository.save(new RefreshToken(null, refreshToken, userId,
            LocalDate.ofInstant(Instant.now().plusSeconds(60 * 60 * 24 * 14), ZoneOffset.UTC)))
        .getRefreshToken();

  }

  /**
   * リフレッシュトークン作成.
   *
   * @param userId ユーザID.
   * @return refreshToken
   */
  public String createRefreshToken(final long userId) {
    final User user = userRepository.findById(userId);

    final String refreshToken = JWT.create().withIssuer(ISSUER)
        .withIssuedAt(Instant.now())
        .withNotBefore(Instant.now()).withExpiresAt(Instant.now().plusSeconds(60 * 60 * 24 * 14))
        .withClaim(USER_NAME_CLAIM, user.getEmail()).sign(Algorithm.HMAC256(SECRET));

    return refreshTokenRepository.save(
            new RefreshToken(null, refreshToken, user.getId(),
                LocalDate.ofInstant(Instant.now().plusSeconds(60 * 60 * 24 * 14), ZoneOffset.UTC)))
        .getRefreshToken();
  }

  /**
   * refreshToken作成.
   *
   * @param ex 認証情報
   * @return refreshToken
   */
  public String createToken(final Authentication ex) {
    //有効期限30分
    return JWT.create().withIssuer(ISSUER).withIssuedAt(Instant.now())
        .withNotBefore(Instant.now()).withExpiresAt(Instant.now().plusSeconds(60 * 30))
        .withClaim(USER_NAME_CLAIM, ex.getName()).sign(Algorithm.HMAC256(SECRET));
  }

  /**
   * token作成.
   *
   * @param userId ユーザID
   * @return token
   */
  public String createToken(final long userId) {

    final User user = userRepository.findById(userId);

    //有効期限30分
    return JWT.create().withIssuer(ISSUER).withIssuedAt(Instant.now())
        .withNotBefore(Instant.now()).withExpiresAt(Instant.now().plusSeconds(60 * 30))
        .withClaim(USER_NAME_CLAIM, user.getEmail()).sign(Algorithm.HMAC256(SECRET));
  }

  /**
   * リフレッシュトークン検証.
   *
   * @param refreshToken リフレッシュトークン
   * @return リフレッシュトークン
   */
  public RefreshToken verifyRefreshToken(final String refreshToken) {
    final RefreshToken findedRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);

    if (!findedRefreshToken.isExpirationDate()) {
      throw new TokenExpiredException("トークンの有効期限が切れています",
          Instant.from(findedRefreshToken.getExpirationDate()));
    }

    return findedRefreshToken;
  }
}
