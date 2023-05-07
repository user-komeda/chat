package com.example.chat.domain.service;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.UserRepository;
import java.util.ArrayList;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * UserDetailServiceImpl.
 */
@Service
@NoArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  /**
   * UserRepository.
   */
  @Autowired
  private UserRepository userRepository;

  /**
   * loadUserByUsername.
   *
   * @param email the username identifying the user whose data is required.
   * @return UserDetails
   */
  @Override
  public UserDetails loadUserByUsername(final String email) {
    final User user = userRepository.findByEmail(email);

    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(), new ArrayList<>());
  }
}
