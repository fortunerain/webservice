package com.farm.test.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerUpdateRequestDto {
  private String name;
  private String address;
  private String phone;

  @Builder
  public CustomerUpdateRequestDto(String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
  }
}
