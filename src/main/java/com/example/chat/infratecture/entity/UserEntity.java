package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/** userEntityClass. */
@Data
@Table("chat_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  /** id. */
  @Id private long id;

  /** roomId. */
  @Column("roomId")
  private Long roomId;

  /** email. */
  private String email;

  /** password. */
  private String password;

  /** userName. */
  @Column("userName")
  private String userName;

  /**
   * コンストラクタ.
   *
   * @param email email.
   * @param password password.
   */
  public UserEntity(
      final Long roomId, final String email, final String password, final String userName) {
    this.roomId = roomId;
    this.email = email;
    this.password = password;
    this.userName = userName;
  }

  /**
   * domainのUserClassへの変換堀.
   *
   * @return com.example.chat.infrastructure.entity.UserEntity
   */
  public User toDomainUser() {
    return new User(this.id, this.roomId, this.email, this.password, this.userName);
  }
}
