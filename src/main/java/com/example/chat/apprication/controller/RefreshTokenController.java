package com.example.chat.apprication.controller;

import com.example.chat.domain.object.RefreshToken;
import com.example.chat.domain.service.CreateTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RefreshTokenController.
 */
@RestController
@RequiredArgsConstructor
public class RefreshTokenController {

  /**
   * CreateTokenService.
   */
  private final transient CreateTokenService createTokenService;

  /**
   * リフレッシュトークン作成.
   *
   * @param refreshTokenCookie cookieの中のrefreshToken値
   * @return ResponseEntity
   */
  @GetMapping("/refreshToken")
  public ResponseEntity<String> createRefreshToken(
      @CookieValue("refreshToken") final String refreshTokenCookie) {

    final RefreshToken refreshToken = createTokenService.verifyRefreshToken(refreshTokenCookie);

    final String createdRefreshToken = createTokenService.createRefreshToken(
        refreshToken.getUserId());
    final String token = createTokenService.createToken(refreshToken.getUserId());
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, createdRefreshToken)
        .header("X-AUTH-TOKEN", token).header("Access-Control-Expose-Headers", "X-AUTH-TOKEN")
        .build();
  }

}
