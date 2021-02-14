package com.benit.team.service;

import com.benit.team.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
  public static final String SUPPER_PASSWORD = "Benit@12345";
  public static final String LOGIN_GOOGLE = "google";
  public static final String LOGIN_FACEBOOK = "facebook";

  @Autowired
  private UserClient userClient;


//  public User getUserWithAuthorities() {
//    User user = this.userClient.getByEmail(SecurityUtils.getCurrentUserLogin()).getData();
//    if (user != null && StringUtils.isEmpty(user.getFullName())) {
//      user.setFullName("BENIT Academy user");
//    }
//    return user;
//  }
}
