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

  /**
   * user取得.
   */
  User findByEmail(String email);

  /**
   * ユーザー取得.
   *
   * @param userId ユーザID
   * @return ユーザー
   */
  User findById(Long userId);

  /**
   * ユーザ存在チェック.
   *
   * @param email email
   * @return 存在しているかどうか
   */
  boolean existsByEmail(String email);

}
