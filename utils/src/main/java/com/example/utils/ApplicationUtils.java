package com.example.utils;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * ApplicationUtils.
 */
@NoArgsConstructor
@Component
public class ApplicationUtils {

  /**
   * encodeしたpasswordを作成.
   *
   * @param password password
   * @return encodedPassword
   */
  public static String createEncodePassword(final String password) {
    return new BCryptPasswordEncoder().encode(password);
  }

  /**
   * passwordEncoder.
   *
   * @return passwordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}