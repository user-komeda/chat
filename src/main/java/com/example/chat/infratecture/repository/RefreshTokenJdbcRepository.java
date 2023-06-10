package com.example.chat.infratecture.repository;

import com.example.chat.infratecture.entity.RefreshTokenEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * RefreshTokenJdbcRepository.
 */
public interface RefreshTokenJdbcRepository extends CrudRepository<RefreshTokenEntity, Long> {

  /**
   * リフレッシュトークン取得.
   *
   * @param refreshToken リフレッシュトークン
   * @return リフレッシュトークン
   */
  Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

}
