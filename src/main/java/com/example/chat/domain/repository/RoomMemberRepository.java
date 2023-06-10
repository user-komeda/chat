package com.example.chat.domain.repository;

import com.example.chat.domain.object.User;
import java.util.List;
import java.util.Optional;

/** RoomMemberRepository. */
public interface RoomMemberRepository {

  /**
   * ルーム内すべてのメンバ取得.
   *
   * @param roomId roomId
   * @return userList
   */
  List<User> findAll(Long roomId);

  /**
   * ルーム内指定したメンバ取得.
   *
   * @param roomId roomId
   * @param id id
   * @return user
   */
  Optional<User> findById(Long roomId, Long id);

  /**
   * ルーム内にメンバ登録.
   *
   * @param roomId roomId
   * @param user user
   */
  void save(Long roomId, User user);

  /**
   * ルーム内メンバ削除.
   *
   * @param id id
   */
  void delete(Long id);
}
