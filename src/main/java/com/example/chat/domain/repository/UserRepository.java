package com.example.chat.domain.repository;

import com.example.chat.domain.object.User;

/**
 * UserRepository.
 */
public interface UserRepository {

  /**
   * saveUser.
   *
   * @param user           user
   * @param encodePassword encodePassword
   * @return user
   */
  User save(User user, String encodePassword);

  /**
   * saveUser.
   *
   * @param user user
   * @return user
   */
  User save(User user);

  /**
   * user検証.
   *
   * @param verificationCode 検証コード
   * @return 検証済みuser
   */
  User findByVerificationCode(String verificationCode);
}
