package com.example.domain.service;

import com.example.domain.exception.UserDeduplicateException;
import com.example.domain.object.User;
import com.example.domain.repository.UserRepository;
import com.example.utils.ApplicationUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 東麓処理serviceClass.
 */
@RequiredArgsConstructor
@Service
public class SigninService {

  /**
   * UserRepository.
   */
  private final transient UserRepository userRepository;

  /**
   * JavaMailSender.
   */
  private final transient JavaMailSender sender;

  /**
   * ユーザ東麓処理.
   *
   * @param user user
   * @return com.example.chat.domain.service.SignupService
   */
  public ResponseEntity<User> signin(final User user) throws MessagingException {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new UserDeduplicateException("ユーザーは既に存在しています");
    }
    final String encodedPassword = ApplicationUtils.createEncodePassword(user.getPassword());
    final User savedUser = userRepository.save(user, encodedPassword);
    this.sendSignupMaile(savedUser.getEmail(), savedUser.getVerificationCode());
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }

  private void sendSignupMaile(final String email, final String verificationCode)
      throws MessagingException {
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
        + "<div>以下のurlをクリックして本登録を完了させてください。</div>"
        + "<a href=http://localhost:8080/verify/" + verificationCode + ">本登録</a>"
        + "</body>"
        + "</html>";

    helper.setText(insertMessage, true);
    //メール送信
    sender.send(message);
  }

  /**
   * mail認証サービス.
   *
   * @param verificationCode 検証コード
   * @return redirect
   */
  public ResponseEntity<String> verify(final String verificationCode) {
    final User user = userRepository.findByVerificationCode(verificationCode);
    if (user.getIsVerified() || !user.getVerificationCode().equals(verificationCode)) {
      throw new UnsupportedOperationException();
    }
    userRepository.save(user);
    return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
        .header(HttpHeaders.LOCATION, "http://localhost:5173").build();
  }
}
