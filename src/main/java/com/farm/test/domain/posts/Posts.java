package com.farm.test.domain.posts;

import com.farm.test.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor  // 기본 생성자 자동 추가
@Entity             //JPA 어노테이션
public class Posts extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 500, nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  private String author;

  @Builder
  public Posts(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  // db 에 update 날리는게 없음.
  // JPA 영속성 컨텍스트(엔티티를 영구 저장하는 환경) 때문.
  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
