package com.example.domain.exception;

import java.io.Serial;

/**
 * TokenNotFoundException.
 */
public class TokenNotFoundException extends RuntimeException {


  @Serial
  private static final long serialVersionUID = -8432890787348521928L;

  /**
   * コンストラクタ.
   *
   * @param msg msg
   */
  public TokenNotFoundException(final String msg) {
    super(msg);
  }
}
