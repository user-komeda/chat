package com.example.application.request;


import com.example.domain.object.Room;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * RoomRequest.
 */
@Data
public class RoomRequest {

  /**
   * roomId.
   */
  private Long id;

  /**
   * roomName.
   */
  @NotBlank
  private String roomName;

  /**
   * 変換処理.
   *
   * @return room
   */
  public Room toDomainRoom() {
    return new Room(this.id, this.roomName);
  }

}
