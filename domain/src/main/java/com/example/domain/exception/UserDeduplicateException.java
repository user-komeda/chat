package com.example.domain.exception;

import java.io.Serial;

/**
 * UserDeduplicateException.
 */
public class UserDeduplicateException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -8957537131763429062L;

  /**
   * UserDeduplicateException.
   *
   * @param msg msg
   */
  public UserDeduplicateException(final String msg) {
    super(msg);
  }
}
