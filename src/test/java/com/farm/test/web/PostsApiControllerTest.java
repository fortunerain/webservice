package com.farm.test.web;

import com.farm.test.domain.posts.Posts;
import com.farm.test.domain.posts.PostsRepository;
import com.farm.test.web.dto.PostsSaveRequestDto;
import com.farm.test.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

  @LocalServerPort
  private int port;

  //@WebMvcTest의 경우 JPA 기능이 작동하지 않기 때문에 TestRestTemplate 사용함.
  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  private PostsRepository postsRepository;
  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;

  @After
  public void tearDown() {
    postsRepository.deleteAll();
  }

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders
      .webAppContextSetup(webApplicationContext)
      .apply(springSecurity())
      .build();
  }

  @Test
  @WithMockUser(roles = "USER")
  public void save() throws Exception {
    //given
    String title = "title";
    String content = "content";
    PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
      .title(title)
      .content(content)
      .author("author")
      .build();

    String url = "http://localhost:" + port + "/api/v1/posts";

    //when
//    ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, postsSaveRequestDto, Long.class);

    //then
//    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    mockMvc.perform(post(url)
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(new ObjectMapper().writeValueAsString(postsSaveRequestDto))
    ).andExpect(status().isOk());

    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title);
    assertThat(all.get(0).getContent()).isEqualTo(content);
  }

  @Test
  @WithMockUser(roles = "USER")
  public void update() throws Exception {
    // given
    Posts savedPosts = postsRepository.save(Posts.builder()
      .title("title")
      .content("content")
      .author("author")
      .build());

    Long updateId = savedPosts.getId();
    String expectedTitle = "title2";
    String expectedContent = "content2";

    PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
      .title(expectedTitle)
      .content(expectedContent)
      .build();

    String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

    HttpEntity<PostsUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(postsUpdateRequestDto);

    //when
//    ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestDtoHttpEntity, Long.class);

    //then
//    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    mockMvc.perform(put(url)
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(new ObjectMapper().writeValueAsString(postsUpdateRequestDto))
    ).andExpect(status().isOk());

    List<Posts> all = postsRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
  }
}