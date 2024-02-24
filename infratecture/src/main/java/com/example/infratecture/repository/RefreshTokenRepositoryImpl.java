package com.example.infratecture.repository;


import com.example.domain.object.RefreshToken;
import com.example.domain.repository.RefreshTokenRepository;
import com.example.infratecture.entity.RefreshTokenEntity;
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
  private final RefreshTokenJdbcRepository refreshTokenJdbcRepository;

  @Override
  public RefreshToken save(final RefreshToken refreshToken) {
    return refreshTokenJdbcRepository.save(RefreshTokenEntity.build(refreshToken))
        .toDomainRefreshToken();
  }

  @Override
  public RefreshToken findByRefreshToken(final String refreshToken) {
    return refreshTokenJdbcRepository.findByRefreshToken(refreshToken)
        .orElseThrow(RuntimeException::new).toDomainRefreshToken();
  }
}
