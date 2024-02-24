package com.example.domain.repository;

import com.example.domain.object.Message;
import java.util.List;

/**
 * MessageRepository.
 */
public interface MessageRepository {

  /**
   * メッセージ一覧取得.
   *
   * @return message
   */
  List<Message> findAll();

  /**
   * メッセージの保存.
   */
  void save(Message message);
}
