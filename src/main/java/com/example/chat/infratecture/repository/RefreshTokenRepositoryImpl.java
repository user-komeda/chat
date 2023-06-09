package com.example.chat.infratecture.repository;


import com.example.chat.domain.object.RefreshToken;
import com.example.chat.domain.repository.RefreshTokenRepository;
import com.example.chat.infratecture.entity.RefreshTokenEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * RefreshTokenRepositoryImpl.
 */
@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

  /**
   * RefreshTokenJdbcRepository.
   */
  @NonNull
  private final RefreshTokenJdbcRepository refreshTokenJdbcRepository;

  @Override
  public RefreshToken save(RefreshToken refreshToken) {
    return refreshTokenJdbcRepository.save(RefreshTokenEntity.build(refreshToken))
        .toDomainRefreshToken();
  }

  @Override
  public RefreshToken findByRefreshToken(String refreshToken) {
    return refreshTokenJdbcRepository.findByRefreshToken(refreshToken)
        .orElseThrow(RuntimeException::new).toDomainRefreshToken();
  }
}
