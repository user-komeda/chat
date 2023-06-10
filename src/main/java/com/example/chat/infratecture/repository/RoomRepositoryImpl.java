package com.example.chat.infratecture.repository;

import com.example.chat.domain.object.Room;
import com.example.chat.domain.repository.RoomRepository;
import com.example.chat.infratecture.entity.RoomEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * RoomRepository実装.
 */
@Repository
@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {

  /**
   * RoomJdbcRepository.
   */
  @NonNull
  private RoomJdbcRepository roomJdbcRepository;

  /**
   * room一覧取得.
   *
   * @return roomList
   */
  @Override
  public List<Room> findAll() {
    final Iterable<RoomEntity> roomEntities = roomJdbcRepository.findAll();
    return StreamSupport.stream(roomEntities.spliterator(), false)
        .map(RoomEntity::toDomainRoom)
        .collect(Collectors.toList());
  }

  /**
   * 指定したroomを取得.
   *
   * @param id id
   * @return room
   */
  @Override
  public Optional<Room> findById(final Long id) {
    return roomJdbcRepository.findById(id).map(RoomEntity::toDomainRoom);
  }

  /**
   * room作成.
   *
   * @param room room
   */
  @Override
  public Room save(final Room room) {
    return roomJdbcRepository
        .save(RoomEntity.buildRoomEntity(room))
        .toDomainRoom();
  }

  /**
   * room編集.
   *
   * @param id   id
   * @param room room
   */
  @Override
  public Room save(final Long id, final Room room) {
    return roomJdbcRepository.save(RoomEntity.buildRoomEntity(room)).toDomainRoom();
  }

  /**
   * room削除.
   *
   * @param id id
   */
  @Override
  public void deleteById(final Long id) {
    roomJdbcRepository.deleteById(id);
  }
}
