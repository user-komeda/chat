package com.example.chat.apprication.controller;

import com.example.chat.domain.object.RefreshToken;
import com.example.chat.domain.service.CreateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshTokenController {

  @Autowired
  CreateTokenService createTokenService;

  @GetMapping("/refreshToken")
  public ResponseEntity<String> createRefreshToken(
      @CookieValue("refreshToken") String refreshTokenCookie) {

    RefreshToken refreshToken = createTokenService.verifyRefreshToken(refreshTokenCookie);

    String createdRefreshToken = createTokenService.createRefreshToken(refreshToken.getUserId());
    String token = createTokenService.createToken(refreshToken.getUserId());
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, createdRefreshToken)
        .header("X-AUTH-TOKEN", token).header("Access-Control-Expose-Headers", "X-AUTH-TOKEN")
        .build();
  }

}
