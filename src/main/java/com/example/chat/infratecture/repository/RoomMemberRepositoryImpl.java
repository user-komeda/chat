package com.example.chat.infratecture.repository;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.RoomMemberRepository;
import com.example.chat.infratecture.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/** RoomMemberRepositoryImpl. */
@Repository
@RequiredArgsConstructor
public class RoomMemberRepositoryImpl implements RoomMemberRepository {

  /** RoomMemberJdbcRepository. */
  @Autowired private RoomMemberJdbcRepository roomMemberJdbcRepository;

  /**
   * ルーム内すべてのメンバ取得.
   *
   * @param roomId roomId
   * @return userList
   */
  @Override
  public List<User> findAll(final Long roomId) {
    final Iterable<UserEntity> roomMemberEntities = roomMemberJdbcRepository.findAll(roomId);
    return StreamSupport.stream(roomMemberEntities.spliterator(), false)
        .map(UserEntity::toDomainUser)
        .collect(Collectors.toList());
  }

  /**
   * ルーム内指定したメンバ取得.
   *
   * @param roomId roomId
   * @param id id
   * @return user
   */
  @Override
  public Optional<User> findById(final Long roomId, final Long id) {
    return roomMemberJdbcRepository.findById(roomId, id).map(UserEntity::toDomainUser);
  }

  /**
   * ルーム内にメンバ登録.
   *
   * @param roomId roomId
   * @param user user
   */
  @Override
  public void save(final Long roomId, final User user) {
    roomMemberJdbcRepository.save(
        user.getId(), roomId, user.getEmail(), user.getPassword(), user.getUserName());
  }

  /**
   * ルーム内メンバ削除.
   *
   * @param id id
   */
  @Override
  public void delete(final Long id) {
    roomMemberJdbcRepository.delete(id);
  }
}
