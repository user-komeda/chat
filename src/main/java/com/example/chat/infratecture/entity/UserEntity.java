package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** userEntityClass. */
@Data
@Table("chat_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  /** id. */
  @Id private long id;

  /** email. */
  private String email;

  /** password. */
  private String password;

  /**
   * コンストラクタ.
   *
   * @param email email.
   * @param password password.
   */
  public UserEntity(final String email, final String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * domainのUserClassへの変換堀.
   *
   * @return com.example.chat.infrastructure.entity.UserEntity
   */
  public User toDomainUser() {
    return new User(this.email, this.password);
  }
}
