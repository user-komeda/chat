package com.example.chat.infratecture.repository;

import com.example.chat.infratecture.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

/** MessageJdbcRepository. */
public interface MessageJdbcRepository extends CrudRepository<MessageEntity, Long> {}
