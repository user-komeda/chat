package com.example.chat.infratecture.repository;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.UserRepository;
import com.example.chat.infratecture.entity.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  @NonNull
  private UserJdbcRepository userJdbcRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public User save(User user) {

    return this.userJdbcRepository.save(new UserEntity(user.getEmail(), passwordEncoder.encode(user.getPassword()))).toDomainUser();
  }

}
