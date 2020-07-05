package com.farm.test.web;

import com.farm.test.config.auth.LoginUser;
import com.farm.test.config.auth.dto.SessionUser;
import com.farm.test.domain.user.Role;
import com.farm.test.service.users.UserService;
import com.farm.test.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {
  private final UserService userService;

  @GetMapping("/user")
  public String user(Model model, @LoginUser SessionUser user) {
    model.addAttribute("users", userService.findAllUser());
    return "user";
  }

  @GetMapping("/user/update/{id}")
  public String userUpdate(@PathVariable Long id, Model model) throws IllegalAccessException, InstantiationException {
    UserResponseDto userResponseDto = userService.findById(id);
    model.addAttribute("user", userResponseDto);
    Role[] roles = Role.class.getEnumConstants();


    for (Role role : roles) {
      if (role.equals(userResponseDto.getRole())) {
        role.setSelected(true);
      }
    }

    // TODO: 2020-07-05 게스트 -> 일반 수정 이후 selected 값이 계속 남아 있는 문제가 있음.
    model.addAttribute("roles", roles);
    System.out.println(roles);
    return "user-update";
  }
}
