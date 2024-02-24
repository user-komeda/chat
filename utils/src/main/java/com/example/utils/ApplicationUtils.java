package com.example.utils;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ApplicationUtils.
 */
@NoArgsConstructor
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
}