package com.example.chat.domain.object;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Message.
 */
@AllArgsConstructor
@Data
public class Message {

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
}
