package com.example.chat.infratecture.entity;

import com.example.chat.domain.object.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** roomEntity. */
@Data
@Table("chat_room")
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {

  /** id. */
  @Id private Long id;

  /** roomName. */
  private String roomName;

  /**
   * 変換処理.
   *
   * @return RoomList
   */
  public Room toDomainRoom() {
    return new Room(this.getId(), this.getRoomName());
  }
}
