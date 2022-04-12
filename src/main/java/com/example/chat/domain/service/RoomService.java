package com.example.chat.domain.service;

import com.example.chat.apprication.resource.RoomBody;
import com.example.chat.domain.object.Room;
import com.example.chat.domain.repository.RoomRepository;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/** roomService. */
@NoArgsConstructor
@Service
public class RoomService {

  /** RoomRepository/. */
  @Autowired private transient RoomRepository roomRepository;

  /**
   * room一覧取得.
   *
   * @return room
   */
  public List<Room> getAllRoom() {

    return roomRepository.findAll();
  }

  /**
   * idを指定してroom情報取得.
   *
   * @param id id
   * @return room
   */
  public Optional<Room> getRoomById(final Long id) {
    return roomRepository.findById(id);
  }

  /**
   * room作成.
   *
   * @param roomBody roomBody
   * @return ResponseEntity
   */
  public ResponseEntity<String> createRoom(final RoomBody roomBody) {
    roomRepository.save(roomBody);
    return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS");
  }

  /**
   * room編集.
   *
   * @param id id
   * @param roomBody roomBody
   * @return ResponseEntity
   */
  public ResponseEntity<String> editRoomById(final Long id, final RoomBody roomBody) {
    roomRepository.save(id, roomBody);
    return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS");
  }

  /**
   * room削除.
   *
   * @param id id
   * @return ResponseEntity
   */
  public ResponseEntity<String> deleteRoom(final Long id) {
    roomRepository.deleteById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("SUCCESS");
  }
}
