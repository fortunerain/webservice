package com.farm.test.web.dto;

import com.farm.test.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersUpdateRequestDto {
  private Role role;

  @Builder
  public UsersUpdateRequestDto(Role role) {
    this.role = role;
  }

}
