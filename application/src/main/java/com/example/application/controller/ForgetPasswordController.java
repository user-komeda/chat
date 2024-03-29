package com.example.application.controller;

import com.example.application.anotation.Email;
import com.example.application.request.ChangePasswordRequest;
import com.example.domain.service.ForgetPasswordService;
import java.util.Map;
import javax.mail.MessagingException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Validated
public class ForgetPasswordController {

  /**
   * ForgetPasswordService.
   */
  private final ForgetPasswordService forgetPasswordService;

  /**
   * パスワード変更メール送信.
   *
   * @param email email
   * @return httpStatus200
   */
  @PostMapping("/forgetPassword")
  public ResponseEntity<String> sendChangePasswordMail(
      @Valid @RequestBody @Email final Map<String, String> email) throws MessagingException {
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
