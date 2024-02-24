package com.example.infratecture.entity;

import com.example.domain.object.Message;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * MessageEntity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("chat_message")
public class MessageEntity {

  /**
   * id.
   */
  @Id
  private Long id;

  /**
   * roomId.
   */
  private Long roomId;

  /**
   * message.
   */
  private String message;

  /**
   * sender.
   */
  private String sender;

  /**
   * destinationUser.
   */
  private String destinationUser;

  /**
   * sendTime.
   */
  private Date sendTime;

  /**
   * updateMessageFlag.
   */
  private boolean updateMessageFlag;

  /**
   * DomainMessageからMessageEntity作成.
   *
   * @param message DomainMessage
   * @return MessageEntity
   */
  public static MessageEntity build(final Message message) {
    return new MessageEntity(
        message.getId(),
        message.getRoomId(),
        message.getMessage(),
        message.getSender(),
        message.getDestinationUser(),
        message.getSendTime(),
        message.isUpdateMessageFlag());
  }

  /**
   * MessageEntityをDomain配下のMessageに変換.
   *
   * @return Domain配下のMessage
   */
  public Message toDomainMessage() {
    return new Message(
        this.id, this.roomId, this.message, this.sender, this.destinationUser, this.sendTime,
        this.updateMessageFlag);
  }
}
