package com.farm.test.web.dto;

import com.farm.test.domain.customer.Customer;
import lombok.Getter;

@Getter
public class CustomerResponseDto {
  private Long id;
  private String name;
  private String address;
  private String phone;

  public CustomerResponseDto(Customer entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.address = entity.getAddress();
    this.phone = entity.getPhone();
  }
}
