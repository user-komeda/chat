package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.RefreshToken;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * RefreshTokenEntity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("refresh_token")
public class RefreshTokenEntity {

  /**
   * id.
   */
  @Id
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

  /**
   * DomainRefreshTokenからRefreshTokenEntity作成.
   *
   * @param refreshToken DomainRefreshToken
   * @return RefreshTokenEntity
   */
  public static RefreshTokenEntity build(final RefreshToken refreshToken) {
    return new RefreshTokenEntity(refreshToken.getId(), refreshToken.getRefreshToken(),
        refreshToken.getUserId(), refreshToken.getExpirationDate());
  }

  /**
   * 変換処理.
   *
   * @return RefreshToken
   */
  public RefreshToken toDomainRefreshToken() {
    return new RefreshToken(this.id, this.refreshToken, this.userId, this.expirationDate);
  }

}
