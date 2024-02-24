package com.example.infratecture.repository;

import com.example.infratecture.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * RoomMemberJdbcRepository.
 */
@Component
public interface RoomMemberJdbcRepository extends CrudRepository<UserEntity, Long> {

  /**
   * ルーム内すべてのメンバ取得.
   *
   * @param roomId roomId
   * @return userList
   */
  @Query(
      "SELECT * "
          + "FROM CHAT_USER "
          + "INNER JOIN CHAT_ROOM ON CHAT_USER.room_id=CHAT_ROOM.id "
          + "WHERE CHAT_ROOM.id=:roomId")
  Iterable<UserEntity> findAll(Long roomId);

  /**
   * ルーム内指定したメンバ取得.
   *
   * @param roomId roomId
   * @param id     id
   * @return user
   */
  @Query(
      "SELECT * "
          + "FROM CHAT_USER "
          + "INNER JOIN CHAT_ROOM ON CHAT_USER.roomId=CHAT_ROOM.id "
          + "WHERE CHAT_ROOM.id=:roomId AND CHAT_USER.id=:id")
  Optional<UserEntity> findById(Long roomId, Long id);

  /**
   * ルーム内にメンバ登録.
   *
   * @param id       id
   * @param roomId   roomId
   * @param email    email
   * @param password password
   * @param userName userName
   */
  @Query(
      "INSERT INTO CHAT_USER(id,roomId,email,password,userName) "
          + "VALUES(:id,:roomId,:email,:password,:userName) "
          + "ON DUPLICATE KEY UPDATE CHAT_USER.roomId = :roomId")
  void save(Long id, Long roomId, String email, String password, String userName);

  /**
   * ルーム内メンバ削除.
   *
   * @param id id
   */
  @Query("UPDATE CHAT_USER " + "SET CHAT_USER.roomId=null " + "WHERE CHAT_USER.id=:id")
  void delete(Long id);
}
