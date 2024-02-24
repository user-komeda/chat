package com.example.domain.object;

import java.security.Principal;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * PrincipalUser.
 */
@Data
@RequiredArgsConstructor
public class PrincipalUser implements Principal {

  /**
   * userName.
   */
  private final String userName;

  @Override
  public String getName() {
    return this.userName;
  }
}
