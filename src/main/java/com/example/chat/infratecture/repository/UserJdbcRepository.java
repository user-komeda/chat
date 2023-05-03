package com.example.chat.infratecture.repository;

import com.example.chat.infratecture.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * springJDBCConfigClass.
 */
public interface UserJdbcRepository extends CrudRepository<UserEntity, String> {

  /**
   * user検証.
   *
   * @param verificationCode 検証コード
   * @return 検証済みuser
   */
  UserEntity findByVerificationCode(String verificationCode);
}
