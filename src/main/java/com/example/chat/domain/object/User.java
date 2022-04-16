package com.example.chat.domain.object;

import lombok.AllArgsConstructor;
import lombok.Data;

/** userObject. */
@Data
@AllArgsConstructor
public class User {

  /** id. */
  private Long id;

  /** roomId. */
  private Long roomId;

  /** email. */
  private String email;

  /** password. */
  private String password;

  /** userName. */
  private String userName;
}
