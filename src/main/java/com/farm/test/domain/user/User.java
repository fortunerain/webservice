package com.farm.test.domain.user;

import com.farm.test.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column
  private String picture;

  //Enum 저장시 기본으로 int가 저장되는데 String으로 저장되도록 함.
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Builder
  public User(Long id, String name, String email, String picture, Role role) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.picture = picture;
    this.role = role;
  }

  public User update(String name, String picture) {
    this.name = name;
    this.picture = picture;
    return this;
  }

  public User update(Role role) {
    this.role = role;
    return this;
  }

  public String getRoleKey() {
    return this.role.getKey();
  }
}
