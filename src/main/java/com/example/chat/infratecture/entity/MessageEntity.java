package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.Message;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** MessageEntity. */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("chat_message")
public class MessageEntity {

  /** id. */
  @Id private Long id;

  /** message. */
  private String message;

  /** sender. */
  private String sender;

  /** roomId. */
  private Long roomId;

  /** sendTime. */
  private Date sendTime;

  /** updateMessageFlag. */
  private boolean updateMessageFlag;

  /**
   * MessageEntityをDomain配下のMessageに変換.
   *
   * @return Domain配下のMessage
   */
  public Message toDomainMessage() {
    return new Message(
        this.id, this.message, this.sender, this.roomId, this.sendTime, this.updateMessageFlag);
  }

  /**
   * DomainMessageからMessageEntity作成.
   *
   * @param message DomainMessage
   * @return MessageEntity
   */
  public MessageEntity build(final Message message) {
    return new MessageEntity(
        message.getId(),
        message.getMessageText(),
        message.getSender(),
        message.getRoomId(),
        message.getSendTime(),
        message.isUpdateMessageFlag());
  }
}
