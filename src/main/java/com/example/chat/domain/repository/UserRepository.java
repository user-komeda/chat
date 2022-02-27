package com.example.chat.domain.repository;

import com.example.chat.domain.object.User;

public interface UserRepository {

  User save(User user);
}
