package com.example.chat.domain.object;

import lombok.AllArgsConstructor;
import lombok.Data;

/** userObject. */
@Data
@AllArgsConstructor
public class User {

  /** email. */
  private String email;

  /** password. */
  private String password;
}
