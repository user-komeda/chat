package com.example.chat.domain.object;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Room object.
 */
@Data
@AllArgsConstructor
public class Room {

  /**
   * id.
   */
  private Long id;

  /**
   * roomName.
   */
  private String roomName;

}
