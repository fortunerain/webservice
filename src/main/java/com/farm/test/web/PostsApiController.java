package com.farm.test.web;

import com.farm.test.config.auth.LoginUser;
import com.farm.test.config.auth.dto.SessionUser;
import com.farm.test.service.posts.PostsService;
import com.farm.test.web.dto.PostsResponseDto;
import com.farm.test.web.dto.PostsSaveRequestDto;
import com.farm.test.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

  private final PostsService postsService;

  @PostMapping("/api/v1/posts")
  public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto, @LoginUser SessionUser user) {
    PostsSaveRequestDto.builder()
            .author(user.getEmail())
            .build();
    return postsService.save(postsSaveRequestDto);
  }

  @PutMapping("/api/v1/posts/{id}")
  public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
    return postsService.update(id, postsUpdateRequestDto);
  }

  @DeleteMapping("/api/v1/posts/{id}")
  public Long delete(@PathVariable Long id) {
    postsService.delete(id);
    return id;
  }

  @GetMapping("/api/v1/posts/{id}")
  public PostsResponseDto findById(@PathVariable Long id) {
    return postsService.findById(id);
  }
}
