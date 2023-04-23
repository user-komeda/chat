package com.example.chat.domain.service;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 東麓処理serviceClass.
 */
@NoArgsConstructor
@Service
public class SignupService {

  /**
   * UserRepository.
   */
  @Autowired
  private transient UserRepository userRepository;

  /**
   * ユーザ東麓処理.
   *
   * @param user user
   * @return com.example.chat.domain.service.SignupService
   */
  public User signup(final User user) {
    return userRepository.save(user);
  }
}
