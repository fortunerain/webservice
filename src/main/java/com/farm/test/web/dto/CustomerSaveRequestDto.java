package com.farm.test.web.dto;

import com.farm.test.domain.customer.Customer;
import com.farm.test.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSaveRequestDto {
  private String name;
  private String address;
  private String phone;
  private String author;

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
