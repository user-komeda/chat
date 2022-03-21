package com.example.chat.domain.service;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

  @Autowired UserRepository userRepository;

  public User signup(User user) {

    User savedUser = userRepository.save(user);
    return userRepository.save(user);
  }
}
