package com.example.chat.domain.object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {


  private String email;

  private String password;

}
