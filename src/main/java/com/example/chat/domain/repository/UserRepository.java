package com.example.chat.domain.repository;

import com.example.chat.domain.object.User;

/**
 * UserRepository.
 */
public interface UserRepository {

  /**
   * save.
   *
   * @param user user
   */
  User save(User user);
}
