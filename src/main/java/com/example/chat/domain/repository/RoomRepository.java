package com.example.chat.domain.repository;

import com.example.chat.apprication.resource.RoomBody;
import com.example.chat.domain.object.Room;
import java.util.List;
import java.util.Optional;

/** RoomRepository. */
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
   * @param roomBody roomBody
   */
  void save(RoomBody roomBody);

  /**
   * room編集.
   *
   * @param id id
   * @param roomBody roomBody
   */
  void save(Long id, RoomBody roomBody);

  /**
   * room削除.
   *
   * @param id id
   */
  void deleteById(Long id);
}
