package com.farm.test.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

  // SpringDataJpa 에서 제공하지 않는 메소드는 아래와 같이 쿼리로 작성 가능함.
  // 규모가 있는 프로젝트에서는 복잡한 쿼리가 많기 때문에 추가 프레임워크를 사용하기도 함.
  // querydsl, jooq, MyBatis 등이 있는데 querydsl 을 추천.
  @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
  List<Posts> findAllDesc();
}
