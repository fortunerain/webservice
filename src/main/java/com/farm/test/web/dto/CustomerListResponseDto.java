package com.farm.test.web.dto;

import com.farm.test.domain.customer.Customer;
import com.farm.test.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomerListResponseDto {
  private Long id;
  private String name;
  private String phone;
  private LocalDateTime modifiedDate;

  public CustomerListResponseDto(Customer entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.phone = entity.getPhone();
    this.modifiedDate = entity.getModifiedDate();
  }
}

