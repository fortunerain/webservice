package com.farm.test.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 해당 클래스를 상속할 경우 아래 필드들을 컬럼으로 인식하게 한다.
@EntityListeners(AuditingEntityListener.class)  // Auditing 기능을 포함시킴
public class BaseTimeEntity {

  @CreatedDate
  private LocalDateTime createDate;

  @LastModifiedDate
  private LocalDateTime modifiedDate;
}
