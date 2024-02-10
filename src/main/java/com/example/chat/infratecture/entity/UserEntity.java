package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.User;
import java.util.UUID;
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
  private Long id;

  /**
   * roomId.
   */
  @Column("room_id")
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
   * verificationCode.
   */
  private String verificationCode;

  /**
   * isVerified.
   */
  @Column("isVerified")
  private Boolean isVerified;

  /**
   * 変換処理.
   *
   * @param user           user
   * @param encodePassword encodePassword
   * @return userEntity
   */
  public static UserEntity buildUserEntity(final User user, final String encodePassword) {
    return new UserEntity(user.getId(), user.getRoomId(), user.getEmail(), encodePassword,
        user.getUserName(), UUID.randomUUID().toString(), Boolean.FALSE);
  }

  /**
   * 変換処理.
   *
   * @param user user
   * @return userEntity
   */
  public static UserEntity buildUserEntity(final User user) {
    return new UserEntity(user.getId(), user.getRoomId(), user.getEmail(), user.getPassword(),
        user.getUserName(), UUID.randomUUID().toString(), Boolean.TRUE);
  }


  /**
   * domainのUserClassへの変換処理.
   *
   * @return com.example.chat.infrastructure.entity.UserEntity
   */
  public User toDomainUser() {
    return new User(this.id, this.roomId, this.email, this.password, this.userName,
        this.verificationCode, this.isVerified);
  }
}
