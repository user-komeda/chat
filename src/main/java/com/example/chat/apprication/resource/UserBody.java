package com.example.chat.apprication.resource;

import com.example.chat.domain.object.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/** userRequestObject. */
@Data
public class UserBody {

  /** id. */
  private Long id;

  /** roomId. */
  private Long roomId;

  /** email. */
  @NotBlank @Email private String email;

  /** password. */
  @NotBlank
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\-]{8,}$")
  private String password;

  /** userName. */
  private String userName;

  /**
   * domain userClassに変換.
   *
   * @return 返還後のuserObject
   */
  public User toDomainUser() {
    return new User(this.id, this.roomId, this.email, this.password, this.userName);
  }
}
