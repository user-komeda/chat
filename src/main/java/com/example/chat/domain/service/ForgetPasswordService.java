package com.example.chat.domain.service;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.UserRepository;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ForgetPasswordService.
 */
@Service
@NoArgsConstructor
public class ForgetPasswordService {

  /**
   * JavaMailSender.
   */
  @Autowired
  private transient JavaMailSender sender;

  /**
   * UserRepository.
   */
  @Autowired
  private transient UserRepository userRepository;

  /**
   * PasswordEncoder.
   */
  @Autowired
  private transient PasswordEncoder passwordEncoder;

  /**
   * パスワード変更メール送信.
   *
   * @param email email
   * @return httpStatus200
   */
  public ResponseEntity<String> sendChangePasswordMail(final String email) {
    final User user = userRepository.findByEmail(email);
    userRepository.save(user);
    final MimeMessage message = sender.createMimeMessage();
    final String fromEmail = "shigoto922@gmail.com";
    try {
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
          + "<a href=http://localhost:8080/changePassword/" + user.getVerificationCode()
          + ">パスワード変更</a>"
          + "</body>"
          + "</html>";

      helper.setText(insertMessage, true);
      //メール送信
      sender.send(message);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (MessagingException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  /**
   * パスワード変更.
   *
   * @param user             user
   * @param verificationCode 検証コード
   * @return httpStatus200
   */
  public ResponseEntity<String> changePassword(final User user, final String verificationCode) {
    final String encodedPassword = passwordEncoder.encode(user.getPassword());
    final User findUser = userRepository.findByVerificationCode(verificationCode);
    findUser.setPassword(encodedPassword);
    userRepository.save(findUser);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
