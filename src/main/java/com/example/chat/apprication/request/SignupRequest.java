package com.example.chat.apprication.request;

import com.example.chat.domain.object.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * SignupRequest.
 */
@Data
public class SignupRequest {

  /**
   * email.
   */
  @NotBlank
  @Email
  private String email;

  /**
   * password.
   */
  @NotBlank
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\-]{8,}$")
  private String password;

  /**
   * user変換.
   *
   * @return user
   */
  public User toDomainUser() {
    return new User(null, null, this.email, this.password, null, null, null);
  }

}
