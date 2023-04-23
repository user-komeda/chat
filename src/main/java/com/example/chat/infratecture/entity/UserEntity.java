package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * userEntityClass.
 */
@Data
@Table("chat_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  /**
   * id.
   */
  @Id
  private long id;

  /**
   * roomId.
   */
  @Column("roomId")
  private Long roomId;

  /**
   * email.
   */
  private String email;

  /**
   * password.
   */
  private String password;

  /**
   * userName.
   */
  @Column("userName")
  private String userName;

  /**
   * 変換処理.
   *
   * @param user user
   * @return userEntity
   */
  public static UserEntity buildUserEntity(final User user) {
    return new UserEntity(user.getId(), user.getRoomId(), user.getEmail(), user.getPassword(),
        user.getUserName());
  }

  /**
   * domainのUserClassへの変換処理.
   *
   * @return com.example.chat.infrastructure.entity.UserEntity
   */
  public User toDomainUser() {
    return new User(this.id, this.roomId, this.email, this.password, this.userName);
  }
}
