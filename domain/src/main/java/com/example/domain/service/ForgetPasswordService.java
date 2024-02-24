package com.example.domain.service;

import com.example.domain.object.User;
import com.example.domain.repository.UserRepository;
import com.example.utils.ApplicationUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * ForgetPasswordService.
 */
@Service
@RequiredArgsConstructor
public class ForgetPasswordService {

  /**
   * JavaMailSender.
   */
  private final transient JavaMailSender sender;

  /**
   * UserRepository.
   */
  private final transient UserRepository userRepository;

  /**
   * パスワード変更メール送信.
   *
   * @param email email
   * @return httpStatus200
   */
  public ResponseEntity<String> sendChangePasswordMail(final String email)
      throws MessagingException {
    final User user = userRepository.findByEmail(email);
    final User savedUser = userRepository.save(user);
    final MimeMessage message = sender.createMimeMessage();
    final String fromEmail = "shigoto922@gmail.com";
    //送信情報設定
    final MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(fromEmail);
    helper.setTo(email);
    helper.setSubject("本登録メール");
    final String insertMessage = "<html>"
        + "<head></head>"
        + "<body>"
        + "<h3>Hello " + email + "</h3>"
        + "<div>以下のurlをクリックしてパスワードを変更してください</div>"
        + "<a href=http://localhost:5173/changePassword/" + savedUser.getVerificationCode()
        + ">パスワード変更</a>"
        + "</body>"
        + "</html>";

    helper.setText(insertMessage, true);
    //メール送信
    sender.send(message);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  /**
   * パスワード変更.
   *
   * @param user             user
   * @param verificationCode 検証コード
   * @return httpStatus200
   */
  public ResponseEntity<String> changePassword(final User user, final String verificationCode) {
    final String encodedPassword = ApplicationUtils.createEncodePassword(user.getPassword());
    final User findUser = userRepository.findByVerificationCode(verificationCode);
    findUser.setPassword(encodedPassword);
    userRepository.save(findUser);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
