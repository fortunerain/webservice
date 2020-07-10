package com.farm.test.web.controller;

import com.farm.test.domain.customer.Customer;
import com.farm.test.service.customer.CustomerService;
import com.farm.test.web.dto.CustomerResponseDto;
import com.farm.test.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class CustomerController {
  private final CustomerService customerService;

  @GetMapping("/customer")
  public String customer(Model model) {
    model.addAttribute("customers", customerService.findAllDesc());
    return "customer";
  }

  @GetMapping("/customer/save")
  public String customerSave() {
    return "customer-save";
  }

  @GetMapping("/customer/update/{id}")
  public String customerUpdate(@PathVariable Long id, Model model) {
    CustomerResponseDto customerResponseDto = customerService.findById(id);
    model.addAttribute("customer", customerResponseDto);
    return "customer-update";
  }
}
