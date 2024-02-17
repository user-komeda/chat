package com.example.chat.infratecture.repository;

import com.example.chat.infratecture.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * springJDBCConfigClass.
 */
public interface UserJdbcRepository extends CrudRepository<UserEntity, Long> {

  /**
   * user検証.
   *
   * @param verificationCode 検証コード
   * @return 検証済みuser
   */
  Optional<UserEntity> findByVerificationCode(String verificationCode);

  /**
   * user取得.
   */
  Optional<UserEntity> findByEmail(String email);


  /**
   * ユーザ存在チェック.
   *
   * @param email email
   * @return ユーザ存在しているかどうか
   */
  boolean existsByEmail(String email);
}
