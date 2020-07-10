package com.farm.test.domain.customer;

import com.farm.test.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity             //JPA 어노테이션
public class Customer extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(length = 500, columnDefinition = "TEXT", nullable = false)
  private String address;

  @Column(nullable = false)
  private String phone;

  private String author;

  @Builder
  public Customer(String name, String address, String phone, String author) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.author = author;
  }

  // db 에 update 날리는게 없음.
  // JPA 영속성 컨텍스트(엔티티를 영구 저장하는 환경) 때문.
  public void update(String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
  }
}
