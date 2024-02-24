package com.example.domain.repository;

import com.example.domain.object.Room;
import java.util.List;
import java.util.Optional;

/**
 * RoomRepository.
 */
public interface RoomRepository {

  /**
   * room一覧取得.
   *
   * @return roomList
   */
  List<Room> findAll();

  /**
   * room検索.
   *
   * @param id id
   * @return room
   */
  Optional<Room> findById(Long id);

  /**
   * room作成.
   *
   * @param room room
   */
  Room save(Room room);

  /**
   * room編集.
   *
   * @param id   id
   * @param room room
   */
  Room save(Long id, Room room);

  /**
   * room削除.
   *
   * @param id id
   */
  void deleteById(Long id);
}
