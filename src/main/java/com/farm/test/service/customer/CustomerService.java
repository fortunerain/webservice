package com.farm.test.service.customer;

import com.farm.test.domain.customer.Customer;
import com.farm.test.domain.customer.CustomerRepository;
import com.farm.test.web.dto.CustomerListResponseDto;
import com.farm.test.web.dto.CustomerResponseDto;
import com.farm.test.web.dto.CustomerSaveRequestDto;
import com.farm.test.web.dto.CustomerUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  @Transactional
  public Long save(CustomerSaveRequestDto customerSaveRequestDto) {
    return customerRepository.save(customerSaveRequestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, CustomerUpdateRequestDto customerUpdateRequestDto) {
    Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 고객이 없습니다. id=" + id));

    customer.update(customerUpdateRequestDto.getName()
            , customerUpdateRequestDto.getAddress()
            , customerUpdateRequestDto.getPhone());
    return id;
  }

  @Transactional
  public void delete(Long id) {
    Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 고객이 없습니다. id=" + id));

    customerRepository.delete(customer);
  }

  public CustomerResponseDto findById(Long id) {
    Customer entity = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 고객이 없습니다. id=" + id));
    return new CustomerResponseDto(entity);
  }

  @Transactional(readOnly = true)
  public List<CustomerListResponseDto> findAllDesc() {
    return customerRepository.findAllDesc().stream()
            .map(CustomerListResponseDto::new)
            .collect(Collectors.toList());
  }
}
