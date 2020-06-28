package com.farm.test.web;

import com.farm.test.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
// @Repository, @Service, @Component, @Configuration는 스캔 대상이 아님. SecurityConfig는 읽었지만 생성하기 위해 필요한 CustomOAuth2UserService 는 읽을수가 없음.
@WebMvcTest(controllers = HelloController.class,
  excludeFilters = {
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
  }
)
public class HelloControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(roles = "USER")
  public void hello() throws Exception {
    String hello = "hello";
    mockMvc.perform(get("/hello"))
      .andExpect(status().isOk())
      .andExpect(content().string(hello));
  }

  @Test
  @WithMockUser(roles = "USER")
  public void helloDto() throws Exception {
    String name = "test";
    int amount = 1000;

    mockMvc.perform(get("/hello/dto")
      .param("name", name)
      .param("amount", String.valueOf(amount)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is(name)))
      .andExpect(jsonPath("$.amount", is(amount)));
  }
}