package com.example.chat.apprication.resource;

import com.example.chat.domain.object.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserBody {

  @NotBlank @Email private String email;

  @NotBlank
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\-]{8,}$")
  private String password;

  public User toDomainUser() {
    return new User(this.email, this.password);
  }
}
