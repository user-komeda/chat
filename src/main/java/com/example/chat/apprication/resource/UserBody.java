package com.example.chat.apprication.resource;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.chat.domain.object.User;

import lombok.Data;

@Data
public class UserBody {

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;

  public User toDomainUser() {
    return new User(this.email, this.password);
  }
}
