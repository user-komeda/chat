package com.example.chat.domain.service;

import com.example.chat.domain.object.User;
import com.example.chat.domain.repository.RoomMemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * RoomMemberService.
 */
@RequiredArgsConstructor
@Service
public class RoomMemberService {

  /**
   * RoomMemberRepository.
   */
  private final transient RoomMemberRepository roomMemberRepository;

  /**
   * ルーム内のすべてのメンバ取得.
   *
   * @param roomId roomId
   * @return userList
   */
  public List<User> getRoomMember(final Long roomId) {
    return roomMemberRepository.findAll(roomId);
  }

  /**
   * ルーム内の指定したメンバ取得.
   *
   * @param roomId roomId
   * @param id     id
   * @return user
   */
  public Optional<User> getRoomMemberById(final Long roomId, final Long id) {
    return roomMemberRepository.findById(roomId, id);
  }

  /**
   * ルーム内にメンバ登録.
   *
   * @param roomId rooId
   * @param user   user
   */
  public void addRoomMember(final Long roomId, final User user) {
    roomMemberRepository.save(roomId, user);
  }

  /**
   * ルーム内のメンバ削除.
   *
   * @param id id
   */
  public void deleteMemberRoom(final Long id) {
    roomMemberRepository.delete(id);
  }
}
