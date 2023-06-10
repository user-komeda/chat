package com.example.chat.domain.repository;

import com.example.chat.domain.object.RefreshToken;

/**
 * RefreshTokenRepository.
 */
public interface RefreshTokenRepository {


  /**
   * refreshTokenの保存.
   *
   * @param refreshToken refreshToken.
   */
  RefreshToken save(RefreshToken refreshToken);

  /**
   * userIdからrefreshToken検索.
   *
   * @param refreshToken refreshtoken
   * @return refreshToken
   */
  RefreshToken findByRefreshToken(String refreshToken);

}
