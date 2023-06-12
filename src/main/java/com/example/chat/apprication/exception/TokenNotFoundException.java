package com.example.chat.apprication.exception;

/**
 * TokenNotFoundException.
 */
public class TokenNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -3584744917248121265L;

  /**
   * コンストラクタ.
   *
   * @param msg msg
   */
  public TokenNotFoundException(final String msg) {
    super(msg);
  }
}
