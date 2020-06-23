package com.farm.test.service.posts;

import com.farm.test.domain.posts.Posts;
import com.farm.test.domain.posts.PostsRepository;
import com.farm.test.web.dto.PostsResponseDto;
import com.farm.test.web.dto.PostsSaveRequestDto;
import com.farm.test.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto postsSaveRequestDto) {
    return postsRepository.save(postsSaveRequestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

    // 트랜젝션안에서 db 데이터를 가져오게 되면 영속성 컨텍스트가 유지된상태.
    // 트랜젝션이 끝나는 시점에 db 변경함. Entity 객체의 값만 변경해주면됨. update 쿼리를 날릴 필요가 없다. -> 더티체킹이라고 함.
    posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
    return id;
  }

  public PostsResponseDto findById(Long id) {
    Posts entity = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    return new PostsResponseDto(entity);
  }
}
