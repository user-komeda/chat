package com.example.infratecture.repository;

import com.example.domain.object.Message;
import com.example.domain.repository.MessageRepository;
import com.example.infratecture.entity.MessageEntity;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * MessageRepositoryImpl.
 */
@RequiredArgsConstructor
@Repository
public class MessageRepositoryImpl implements MessageRepository {

  /**
   * messageJdbcRepository.
   */
  private final MessageJdbcRepository messageJdbcRepository;

  /**
   * メッセージ一覧の取得.
   *
   * @return message
   */
  @Override
  public List<Message> findAll() {
    final Iterable<MessageEntity> messageEntities = messageJdbcRepository.findAll();
    return StreamSupport.stream(messageEntities.spliterator(), false)
        .map(MessageEntity::toDomainMessage)
        .collect(Collectors.toList());
  }

  /**
   * メッセージの保存.
   *
   * @param message message
   */
  @Override
  public void save(final Message message) {
    messageJdbcRepository.save(MessageEntity.build(message));
  }
}
