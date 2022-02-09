package com.example.chat.apprication.controller;

import com.example.chat.apprication.resource.UserBody;
import com.example.chat.domain.object.User;
import com.example.chat.domain.service.SignupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  SignupService signupService;

  @PostMapping("/signup")
  public User signup(@RequestBody @Validated UserBody userBody) {
    return signupService.signup(userBody.toDomainUser());
  }
}
