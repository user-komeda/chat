package com.example.domain.service;

import com.example.domain.object.User;
import com.example.domain.repository.UserRepository;
import java.util.ArrayList;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * UserDetailServiceImpl.
 */
@Service
@RequiredArgsConstructor
public final class UserDetailServiceImpl implements UserDetailsService {

  /**
   * UserRepository.
   */
  @NonNull
  private final UserRepository userRepository;

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
