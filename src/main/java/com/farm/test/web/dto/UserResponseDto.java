package com.farm.test.web.dto;

import com.farm.test.domain.user.Role;
import com.farm.test.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
  private Long id;
  private String email;
  private String name;
  private Role role;

  public UserResponseDto(User entity) {
    this.id = entity.getId();
    this.email = entity.getEmail();
    this.name = entity.getName();
    this.role = entity.getRole();
  }
}
