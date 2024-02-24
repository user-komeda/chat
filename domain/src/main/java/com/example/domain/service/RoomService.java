package com.example.domain.service;

import com.example.domain.object.Room;
import com.example.domain.repository.RoomRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * roomService.
 */
@RequiredArgsConstructor
@Service
public class RoomService {

  /**
   * RoomRepository/.
   */
  private final transient RoomRepository roomRepository;

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
   * @param room room
   * @return ResponseEntity
   */
  public ResponseEntity<Room> createRoom(final Room room) throws JsonProcessingException {
    final Room createdRoom = roomRepository.save(room);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(createdRoom);
  }

  /**
   * room編集.
   *
   * @param id   id
   * @param room room
   * @return ResponseEntity
   */
  public ResponseEntity<Room> editRoomById(final Long id, final Room room) {
    final Room updatedRoom = roomRepository.save(id, room);
    return ResponseEntity.status(HttpStatus.CREATED).body(updatedRoom);
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
