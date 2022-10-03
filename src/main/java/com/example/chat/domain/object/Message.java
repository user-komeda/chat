package com.example.chat.domain.object;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

/** Message. */
@AllArgsConstructor
@Data
public class Message {
  /** id. */
  @Id private Long id;

  /** message. */
  private String messageText;

  /** sender. */
  private String sender;

  /** roomId. */
  private Long roomId;

  /** sendTime. */
  private Date sendTime;

  /** updateMessageFlag. */
  private boolean updateMessageFlag;
}
