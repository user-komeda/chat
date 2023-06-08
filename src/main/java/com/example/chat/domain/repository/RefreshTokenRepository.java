package com.example.chat.domain.repository;

import com.example.chat.domain.object.RefreshToken;

/**
 * RefreshTokenRepository.
 */
public interface RefreshTokenRepository {


  /**
   * refreshTokenの保存
   *
   * @param refreshToken refreshToken.
   */
  RefreshToken save(final RefreshToken refreshToken);

  /**
   * userIdからrefreshToken検索.
   *
   * @param userId userId
   * @return refreshToken
   */
  RefreshToken findByUserId(Long userId);

}
