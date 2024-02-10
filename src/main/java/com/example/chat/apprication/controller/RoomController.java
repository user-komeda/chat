package com.example.chat.apprication.controller;

import com.example.chat.apprication.request.RoomRequest;
import com.example.chat.domain.object.Room;
import com.example.chat.domain.service.RoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * roomController.
 */
@RestController
@RequestMapping("/room")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
@Validated
public class RoomController {

  /**
   * roomService.
   */
  private final transient RoomService roomService;

  /**
   * すべてのルームを取得.
   */
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public List<Room> getAllRoom() {
    return roomService.getAllRoom();
  }

  /**
   * 指定したroomを取得.
   *
   * @param id id
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Room> getRoomById(@PathVariable final Long id) {
    return roomService.getRoomById(id);
  }

  /**
   * room作成.
   *
   * @param roomRequest roomRequest
   */
  @PostMapping("/create")
  public ResponseEntity<Room> createRoom(@Valid @RequestBody final RoomRequest roomRequest)
      throws JsonProcessingException {
    return roomService.createRoom(roomRequest.toDomainRoom());
  }

  /**
   * room編集.
   *
   * @param id          id
   * @param roomRequest roomRequest
   */
  @PatchMapping("/create/{id}")
  public ResponseEntity<Room> editRoomById(
      @PathVariable final Long id, @Valid @RequestBody final RoomRequest roomRequest) {
    return roomService.editRoomById(id, roomRequest.toDomainRoom());
  }

  /**
   * room削除.
   *
   * @param id id
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteRoomById(@PathVariable final Long id) {
    return roomService.deleteRoom(id);
  }
}
