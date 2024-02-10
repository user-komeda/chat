package com.example.chat.domain.service;

import com.example.chat.domain.object.Message;
import com.example.chat.domain.repository.MessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * MessageService.
 */
@RequiredArgsConstructor
@Service
public class MessageService {

  /**
   * messageRepository.
   */
  private final transient MessageRepository messageRepository;

  /**
   * メッセージの保存.
   *
   * @param message message
   */
  public void saveMessage(final Message message) {
    messageRepository.save(message);
  }

  /**
   * メッセージの取得.
   *
   * @return message
   */
  public List<Message> getAllMessage() {
    return messageRepository.findAll();
  }
}
