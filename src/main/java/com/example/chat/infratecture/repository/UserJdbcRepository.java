package com.example.chat.infratecture.repository;

import com.example.chat.infratecture.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/** springJDBCConfigClass. */
public interface UserJdbcRepository extends CrudRepository<UserEntity, String> {}
