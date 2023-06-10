package com.example.chat.infratecture.repository;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.UserRepository;
import com.example.chat.infratecture.entity.UserEntity;
import java.util.Optional;
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
  public User save(final User user, final String encodePassword) {
    return this.userJdbcRepository.save(UserEntity.buildUserEntity(user, encodePassword))
        .toDomainUser();
  }

  @Override
  public User save(final User user) {
    return this.userJdbcRepository.save(UserEntity.buildUserEntity(user)).toDomainUser();
  }

  @Override
  public User findByVerificationCode(final String verificationCode) {
    final Optional<UserEntity> userEntity = this.userJdbcRepository.findByVerificationCode(
        verificationCode);
    return userEntity.orElseThrow(RuntimeException::new).toDomainUser();
  }

  @Override
  public User findByEmail(final String email) {
    final Optional<UserEntity> userEntity = this.userJdbcRepository.findByEmail(email);
    return userEntity.orElseThrow(RuntimeException::new).toDomainUser();
  }

  @Override
  public User findById(final Long userId) {
    final Optional<UserEntity> userEntity = this.userJdbcRepository.findById(userId);
    return userEntity.orElseThrow(RuntimeException::new).toDomainUser();
  }
}
