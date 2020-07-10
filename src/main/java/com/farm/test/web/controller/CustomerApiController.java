package com.farm.test.web.controller;

import com.farm.test.config.auth.LoginUser;
import com.farm.test.config.auth.dto.SessionUser;
import com.farm.test.domain.customer.Customer;
import com.farm.test.service.customer.CustomerService;
import com.farm.test.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CustomerApiController {

  private final CustomerService customerService;

  @PostMapping("/api/v1/customer")
  public Long save(@RequestBody CustomerSaveRequestDto customerSaveRequestDto, @LoginUser SessionUser user) {
    // junit test 를 위해 null 일때 체크.
    customerSaveRequestDto.setAuthor(user == null ? "Invalid User" : user.getEmail());
    return customerService.save(customerSaveRequestDto);
  }

  @PutMapping("/api/v1/customer/{id}")
  public Long update(@PathVariable Long id, @RequestBody CustomerUpdateRequestDto customerUpdateRequestDto) {
    return customerService.update(id, customerUpdateRequestDto);
  }

  @DeleteMapping("/api/v1/customer/{id}")
  public Long delete(@PathVariable Long id) {
    customerService.delete(id);
    return id;
  }

  @GetMapping("/api/v1/customer/{id}")
  public CustomerResponseDto findById(@PathVariable Long id) {
    return customerService.findById(id);
  }
}
