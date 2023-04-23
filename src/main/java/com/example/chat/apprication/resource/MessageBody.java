package com.example.chat.apprication.resource;

import com.example.chat.domain.object.Message;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * MessageBody.
 */
@Data
public class MessageBody {

  /**
   * id.
   */
  private Long id;

  /**
   * roomId.
   */
  @NotNull
  private Long roomId;

  /**
   * message.
   */
  @NotBlank
  private String message;

  /**
   * sender.
   */
  @NotBlank
  private String sender;

  /**
   * destinationUser.
   */
  private String destinationUser;


  /**
   * sendTime.
   */
  @NotNull
  private Date sendTime;

  /**
   * MessageBodyからDomainMessageに変換.
   *
   * @return DomainMessage
   */
  public Message toDomainMessage() {
    return new Message(this.id, this.roomId, this.message, this.sender, this.destinationUser,
        this.sendTime, false);
  }
}
