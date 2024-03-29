package com.example.application.controller;

import com.example.application.request.SigninRequest;
import com.example.domain.object.User;
import com.example.domain.service.SigninService;
import javax.mail.MessagingException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * user controller.
 */
@RestController
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Validated
public class SignInController {


  /**
   * SignupService.
   */
  private final SigninService signinService;

  /**
   * ユーザ登録.
   *
   * @param signinRequest signinRequest
   * @return 登録されたuser情報
   */
  @PostMapping("/signin")
  public ResponseEntity<User> signin(@RequestBody @Valid final SigninRequest signinRequest)
      throws MessagingException {
    return signinService.signin(signinRequest.toDomainUser());
  }

  /**
   * mail認証.
   *
   * @param verificationCode 検証コード
   * @return redirect
   */
  @GetMapping("/verify/{verificationCode}")
  public ResponseEntity<String> verify(@PathVariable final String verificationCode) {
    return signinService.verify(verificationCode);
  }
}
