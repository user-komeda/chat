package com.example.domain.object;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * RefreshToken.
 */
@AllArgsConstructor
@Getter
@Setter
public class RefreshToken {

  /**
   * id.
   */
  private Long id;

  /**
   * refreshToken.
   */
  private String refreshToken;

  /**
   * userId.
   */
  private Long userId;

  /**
   * expirationDate.
   */
  private LocalDate expirationDate;

  public boolean isExpirationDate() {
    return expirationDate.isAfter(LocalDate.now());
  }

}
