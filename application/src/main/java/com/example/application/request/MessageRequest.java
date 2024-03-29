package com.example.application.request;

import com.example.domain.object.Message;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * MessageRequest.
 */
@Data
public class MessageRequest {

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
