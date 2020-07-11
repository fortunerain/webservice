package com.farm.test.web.dto;

import com.farm.test.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerSaveRequestDto {
  private String name;
  private String address;
  private String phone;
  private String author;

  @Builder
  public CustomerSaveRequestDto(String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Customer toEntity() {
    return Customer.builder()
            .name(name)
            .address(address)
            .phone(phone)
            .author(author)
            .build();
  }
}
