package com.example.chat.apprication.resource;

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
}
