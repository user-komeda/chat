package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("chat_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
@Id
private  int Id;

  private String email;

  private String password;

  public UserEntity(String email, String password) {
    this.email=email;
    this.password=password;
  }

  public User toDomainUser() {
    return new User(this.email, this.password);
  }
}
