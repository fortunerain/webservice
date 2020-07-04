package com.farm.test.web.dto;

import com.farm.test.domain.user.Role;
import com.farm.test.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserListResponseDto {
  private Long id;
  private String email;
  private String name;
  private Role role;
  private LocalDateTime modifiedDate;

  public UserListResponseDto(User entity) {
    this.id = entity.getId();
    this.email = entity.getEmail();
    this.name = entity.getName();
    this.role = entity.getRole();
    this.modifiedDate = entity.getModifiedDate();
  }
}
