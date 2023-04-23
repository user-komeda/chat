package com.example.chat.infratecture.repository;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.UserRepository;
import com.example.chat.infratecture.entity.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * UserRepository実装Class.
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  /**
   * UserJdbcRepository.
   */
  @NonNull
  private UserJdbcRepository userJdbcRepository;

  /**
   * PasswordEncoder.
   */
  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * ユーザ情報東麓.
   *
   * @param user user
   * @return 東麓結果
   */
  @Override
  public User save(final User user) {
    return this.userJdbcRepository
        .save(UserEntity.buildUserEntity(user)).toDomainUser();
  }
}
