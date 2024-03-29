package com.example.application.request;

import com.example.domain.object.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * ChangePasswordRequest.
 */
@Data
public class ChangePasswordRequest {

  /**
   * email.
   */
  @Email
  private String email;

  /**
   * password.
   */
  @NotBlank
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\-]{8,}$")
  private String password;

  /**
   * password.
   */
  @NotBlank
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\-]{8,}$")
  private String confirmPassword;

  /**
   * user変換.
   *
   * @return user
   */
  public User toDomainUser() {
    return new User(null, null, this.email, this.password, null, null, null);
  }
}
