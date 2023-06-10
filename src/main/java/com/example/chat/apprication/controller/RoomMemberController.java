package com.example.chat.apprication.controller;

import com.example.chat.apprication.resource.SigninBody;
import com.example.chat.domain.object.User;
import com.example.chat.domain.service.RoomMemberService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * RoomMemberController.
 */
@RestController
@NoArgsConstructor
@RequestMapping("/room/{roomId}/member")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Validated
public class RoomMemberController {

  /**
   * RoomMemberService.
   */
  @Autowired
  private transient RoomMemberService roomMemberService;

  /**
   * ルーム内のメンバー一覧を取得.
   *
   * @param roomId roomId
   * @return userList
   */
  @GetMapping("/")
  @ResponseStatus(HttpStatus.OK)
  public List<User> getRoomMember(@PathVariable final Long roomId) {
    return roomMemberService.getRoomMember(roomId);
  }

  /**
   * ルーム内の指定したメンバーを取得.
   *
   * @param roomId roomId
   * @param id     id
   * @return user
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<User> getRoomMemberById(
      @PathVariable final Long roomId, @PathVariable final Long id) {
    return roomMemberService.getRoomMemberById(roomId, id);
  }

  /**
   * ルーム内にメンバー東麓.
   *
   * @param roomId     roomId
   * @param signinBody userBody
   */
  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public void addRoomMember(@PathVariable final Long roomId,
      @Valid @RequestBody final SigninBody signinBody) {
    roomMemberService.addRoomMember(roomId, signinBody.toDomainUser());
  }

  /**
   * ルーム内のメンバーを削除.
   *
   * @param id id
   */
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMemberRoom(@PathVariable final Long id) {
    roomMemberService.deleteMemberRoom(id);
  }
}
