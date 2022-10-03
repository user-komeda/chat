package com.example.chat.apprication.resource;

import com.example.chat.domain.object.Message;
import java.util.Date;
import lombok.Data;

/** MessageBody. */
@Data
public class MessageBody {

  /** id. */
  private Long id;

  /** userName. */
  private String userName;

  /** message. */
  private String message;

  /** sendTime. */
  private Date sendTime;

  /**
   * MessageBodyからDomainMessageに変換.
   *
   * @return DomainMessage
   */
  public Message toDomainMessage() {
    return new Message(null, this.message, this.userName, this.id, this.sendTime, false);
  }
}
