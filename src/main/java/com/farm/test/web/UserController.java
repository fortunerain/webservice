package com.farm.test.web;

import com.farm.test.config.auth.LoginUser;
import com.farm.test.config.auth.dto.SessionUser;
import com.farm.test.domain.user.Role;
import com.farm.test.service.users.UserService;
import com.farm.test.web.dto.SelectOption;
import com.farm.test.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {
  private final UserService userService;

  @GetMapping("/user")
  public String user(Model model, @LoginUser SessionUser user) {
    model.addAttribute("users", userService.findAllUser());
    if (user != null) {
      model.addAttribute("loginUserName", user.getName());
    }

    return "user";
  }

  @GetMapping("/user/update/{id}")
  public String userUpdate(@PathVariable Long id, Model model) {
    UserResponseDto userResponseDto = userService.findById(id);
    model.addAttribute("user", userResponseDto);
    Role[] roles = Role.class.getEnumConstants();

    List<SelectOption> selectOptions = new ArrayList<>();
    for (Role role : roles) {
      SelectOption selectOption = new SelectOption(role.getKey(), role.name());
      if (role.equals(userResponseDto.getRole())) {
        selectOption.setSelected("selected");
      } else {
        selectOption.setSelected("");
      }
      selectOptions.add(selectOption);
    }

    model.addAttribute("roles", selectOptions);
    return "user-update";
  }
}
