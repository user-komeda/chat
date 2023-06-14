package com.example.chat.apprication.controller;

import com.example.chat.apprication.anotation.Email;
import com.example.chat.apprication.request.ChangePasswordRequest;
import com.example.chat.domain.service.ForgetPasswordService;
import java.util.Map;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ForgetPasswordController.
 */
@RestController
@NoArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Validated
public class ForgetPasswordController {

  /**
   * ForgetPasswordService.
   */
  @Autowired
  private ForgetPasswordService forgetPasswordService;

  /**
   * パスワード変更メール送信.
   *
   * @param email email
   * @return httpStatus200
   */
  @PostMapping("/forgetPassword")
  public ResponseEntity<String> sendChangePasswordMail(
      @Valid @RequestBody @Email final Map<String, String> email) {
    return forgetPasswordService.sendChangePasswordMail(email.get("email"));
  }

  /**
   * パスワード変更.
   *
   * @param changePasswordRequest changePasswordRequest
   * @param verificationCode      verificationCode
   * @return httpStatus200
   */
  @PostMapping("/changePassword/{verificationCode}")
  public ResponseEntity<String> changePassword(
      @Valid @RequestBody final ChangePasswordRequest changePasswordRequest,
      @PathVariable final String verificationCode) {
    return forgetPasswordService.changePassword(changePasswordRequest.toDomainUser(),
        verificationCode);
  }
}
