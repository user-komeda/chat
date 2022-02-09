package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.User;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  private String email;

  private String password;

  public User toDomainUser() {
    return new User(this.email, this.password);
  }
}
