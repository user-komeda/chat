package com.example.chat.apprication.controller;

import com.example.chat.apprication.resource.SigninBody;
import com.example.chat.domain.object.User;
import com.example.chat.domain.service.SigninService;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@NoArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Validated
public class SignInController {


  /**
   * SignupService.
   */
  @Autowired
  private SigninService signinService;

  /**
   * ユーザ登録.
   *
   * @param signinBody userBody
   * @return 登録されたuser情報
   */
  @PostMapping("/signin")
  public ResponseEntity<User> signin(@RequestBody @Valid final SigninBody signinBody) {
    return signinService.signin(signinBody.toDomainUser());
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
