package com.example.chat.apprication.controller;

import com.example.chat.apprication.resource.UserBody;
import com.example.chat.domain.object.User;
import com.example.chat.domain.service.SignupService;
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
public class UserController {


  /**
   * SignupService.
   */
  @Autowired
  private SignupService signupService;

  /**
   * ユーザ登録.
   *
   * @param userBody userBody
   * @return 登録されたuser情報
   */
  @PostMapping("/signup")
  public ResponseEntity<User> signup(@RequestBody @Valid final UserBody userBody) {
    return signupService.signup(userBody.toDomainUser());
  }

  /**
   * mail認証.
   *
   * @param verificationCode 検証コード
   * @return redirect
   */
  @GetMapping("/verify/{verificationCode}")
  public ResponseEntity<String> verify(@PathVariable final String verificationCode) {
    return signupService.verify(verificationCode);
  }
}
