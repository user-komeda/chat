package com.example.infratecture.repository;

import com.example.infratecture.entity.RefreshTokenEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * RefreshTokenJdbcRepository.
 */
@Component
@Repository
public interface RefreshTokenJdbcRepository extends CrudRepository<RefreshTokenEntity, Long> {

  /**
   * リフレッシュトークン取得.
   *
   * @param refreshToken リフレッシュトークン
   * @return リフレッシュトークン
   */
  Optional<RefreshTokenEntity> findByRefreshToken(String refreshToken);

}
