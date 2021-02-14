package com.benit.team.controller;

import com.benit.team.base.response.ResponseData;
import com.benit.team.client.UserClient;
import com.benit.team.dto.user.User;
import com.benit.team.dto.user.UserFilter;
//import com.benit.team.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/api/users")
@Slf4j
public class UserController {
//  @Autowired
//  private UserService userService;

  @Autowired
  private UserClient userClient;

  @GetMapping("/test")
  public String testApi() {
    return "This is BENIT Academy API";
  }

  //Get all user from user service
  @GetMapping("/all")
  public ResponseEntity<ResponseData<List<User>>> getAllUser(UserFilter filter) {
    return new ResponseEntity<>(this.userClient.getAll(filter), HttpStatus.OK);
  }

  //Get user by ID
  @GetMapping("/{id}")
  public ResponseData<User> getAllUserById(@PathVariable String id) {
    return this.userClient.getById(id);
  }


}
