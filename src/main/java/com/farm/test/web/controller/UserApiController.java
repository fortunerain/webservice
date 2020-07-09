package com.farm.test.web.controller;

import com.farm.test.service.users.UserService;
import com.farm.test.web.dto.PostsResponseDto;
import com.farm.test.web.dto.PostsUpdateRequestDto;
import com.farm.test.web.dto.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

  private final UserService userService;

  @PutMapping("/api/v1/user/{id}")
  public Long update(@PathVariable Long id, @RequestBody UsersUpdateRequestDto usersUpdateRequestDto) {
    return userService.update(id, usersUpdateRequestDto.getRole());
  }
}
