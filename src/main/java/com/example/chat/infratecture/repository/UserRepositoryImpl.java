package com.example.chat.infratecture.repository;

import javax.validation.constraints.NotNull;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.UserRepository;
import com.example.chat.infratecture.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  @NonNull
  private UserJdbcRepository userJdbcRepository;

  @Override
  public User save(User user) {
    return this.userJdbcRepository.save(new UserEntity(user.getEmail(), user.getPassword())).toDomainUser();
  }

}
