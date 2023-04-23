package com.example.chat.apprication.controller;

import com.example.chat.apprication.resource.MessageBody;
import com.example.chat.domain.object.Message;
import com.example.chat.domain.service.MessageService;
import java.util.List;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MessageController.
 */
@RestController
@NoArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Validated
public class MessageController {

  /**
   * SimpMessagingTemplate.
   */
  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  /**
   * SaveMessageService .
   */
  @Autowired
  private MessageService messageService;

  /**
   * ルームあてのメッセージを処理.
   *
   * @param body クライアントから送られたメッセージ
   * @return クライアントにメッセージを返却
   */
  @MessageMapping("/{id}/messages")
  @SendTo("/topic/{id}/messages")
  public String sendMessage(@Valid final MessageBody body) {
    messageService.saveMessage(body.toDomainMessage());
    return body.getMessage();
  }

  /**
   * ダイレクトメッセージを処理.
   *
   * @param body クライアントから送られたメッセージ
   */
  @MessageMapping("/private")
  public void sendPrivateMessage(@Valid final MessageBody body) {
    messageService.saveMessage(body.toDomainMessage());
    simpMessagingTemplate.convertAndSendToUser(body.getDestinationUser(), "/queue/messages",
        body);
  }

  /**
   * メッセージ一覧の取得.
   *
   * @return メッセージ一覧
   */
  @GetMapping("/message")
  public List<Message> getAllMessage() {
    return messageService.getAllMessage();
  }
}