package com.example.chat.apprication.controller;

import com.example.chat.domain.service.CreateTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    System.out.println(refreshTokenCookie);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    boolean isVerify = createTokenService.verifyRefreshToken(refreshTokenCookie,
        authentication.getName());

    if (isVerify) {
      String refreshToken = createTokenService.crateRefreshToken(authentication);
      String token = createTokenService.createToken(authentication);
      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, refreshToken)
          .header("X-AUTH-TOKEN", token).header("Access-Control-Expose-Headers", "X-AUTH-TOKEN")
          .build();
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

}
