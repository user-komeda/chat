package com.example.chat.apprication.resource;


import com.example.chat.domain.object.Room;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * roomBodyObject.
 */
@Data
public class RoomBody {

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
