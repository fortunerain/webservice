package com.farm.test.web;

import com.farm.test.config.auth.LoginUser;
import com.farm.test.config.auth.dto.SessionUser;
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
  public String userUpdate(@PathVariable Long id, Model model) {
    UserResponseDto userResponseDto = userService.findById(id);
    model.addAttribute("user", userResponseDto);
    return "user-update";
  }
}
