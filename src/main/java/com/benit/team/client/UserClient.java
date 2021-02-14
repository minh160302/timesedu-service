package com.benit.team.client;

import com.benit.team.base.response.ResponseData;
import com.benit.team.dto.user.User;
import com.benit.team.dto.user.UserFilter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
@FeignClient(value = "user-service", url = "http://localhost:9205")
public interface UserClient {

  //Get all users by userFilter
  @RequestMapping(method = RequestMethod.GET, value = "/users")
  ResponseData<List<User>> getAll(@SpringQueryMap UserFilter filter);

  //Get all users by userId
  @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
  ResponseData<User> getById(@PathVariable String id);

  //Create a new user
  @RequestMapping(method = RequestMethod.POST, value = "/users")
  ResponseData<User> create(@RequestBody User user);

  //Update new user to database
  @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
  ResponseData<User> update(@RequestBody User user, @PathVariable String id);

  //Get user by email
  @RequestMapping(method = RequestMethod.GET, value = "/users/email/{email}")
  ResponseData<User> getByEmail(@PathVariable String email);

  //Get user by activation key
  @RequestMapping(method = RequestMethod.GET, value = "/users/activationKey/{activationKey}")
  ResponseData<User> getByActivationKey(@PathVariable String activationKey);
}
