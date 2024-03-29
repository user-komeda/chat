package com.example.application.controller;

import com.example.application.request.MessageRequest;
import com.example.domain.object.Message;
import com.example.domain.service.MessageService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Validated
public class MessageController {

  /**
   * SimpMessagingTemplate.
   */
  private final transient SimpMessagingTemplate simpMessagingTemplate;

  /**
   * SaveMessageService .
   */
  private final transient MessageService messageService;

  /**
   * ルームあてのメッセージを処理.
   *
   * @param request クライアントから送られたメッセージ
   * @return クライアントにメッセージを返却
   */
  @MessageMapping("/{id}/messages")
  @SendTo("/topic/{id}/messages")
  public String sendMessage(@Valid final MessageRequest request) {
    messageService.saveMessage(request.toDomainMessage());
    return request.getMessage();
  }

  /**
   * ダイレクトメッセージを処理.
   *
   * @param request クライアントから送られたメッセージ
   */
  @MessageMapping("/private")
  public void sendPrivateMessage(@Valid final MessageRequest request) {
    messageService.saveMessage(request.toDomainMessage());
    simpMessagingTemplate.convertAndSendToUser(request.getDestinationUser(), "/queue/messages",
        request);
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
