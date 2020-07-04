package com.farm.test.config.auth.dto;

import com.farm.test.domain.user.Role;
import com.farm.test.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
  private String name;
  private String email;
  private String picture;
  private Role role;

  public SessionUser(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.picture = user.getPicture();
    this.role = user.getRole();
  }
}
