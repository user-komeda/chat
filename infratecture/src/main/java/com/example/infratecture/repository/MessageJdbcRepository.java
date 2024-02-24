package com.example.infratecture.repository;

import com.example.infratecture.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * MessageJdbcRepository.
 */
@Component
public interface MessageJdbcRepository extends CrudRepository<MessageEntity, Long> {

}
