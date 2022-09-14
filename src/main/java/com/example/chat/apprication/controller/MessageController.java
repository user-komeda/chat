package com.example.chat.apprication.controller;

import com.example.chat.apprication.resource.MessageBody;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/** MessageController. */
@RestController
@NoArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MessageController {

  /** SimpMessagingTemplate. */
  @Autowired private SimpMessagingTemplate simpMessagingTemplate;

  /**
   * ルームあてのメッセージを処理.
   *
   * @param body クライアントから送られたメッセージ
   * @return クライアントにメッセージを返却
   */
  @MessageMapping("/{id}/messages")
  @SendTo("/channels/1")
  public String sendMessage(final MessageBody body) {
    return body.getMessage();
  }

  /**
   * ダイレクトメッセージを処理.
   *
   * @param body クライアントから送られたメッセージ
   */
  @MessageMapping("{id}/users/{userName}/messages")
  public void sendPrivateMessage(final MessageBody body) {
    simpMessagingTemplate.convertAndSendToUser(body.getUserName(), "/msg", body);
  }
}
