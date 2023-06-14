package com.example.chat.apprication.request;

import com.example.chat.domain.object.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * SigninBody.
 */
@Data
public class SigninRequest {


  /**
   * id.
   */
  private Long id;

  /**
   * roomId.
   */
  private Long roomId;

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
   * password確認.
   */
  @NotBlank
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\-]{8,}$")
  private String confirmPassword;

  /**
   * userName.
   */
  @NotBlank
  private String userName;

  /**
   * verificationCode.
   */
  private String verificationCode;

  /**
   * isVerified.
   */
  private Boolean isVerified;

  /**
   * domain userClassに変換.
   *
   * @return 返還後のuserObject
   */
  public User toDomainUser() {
    return new User(this.id, this.roomId, this.email, this.password,
        this.userName, this.verificationCode, isVerified);
  }
}
