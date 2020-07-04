package com.farm.test.web;

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

  @PutMapping("/api/v1/users/{email}")
  public Long update(@PathVariable String email, @RequestBody UsersUpdateRequestDto usersUpdateRequestDto) {
    return userService.update(email, usersUpdateRequestDto.getRole());
  }
}
