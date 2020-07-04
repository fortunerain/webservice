package com.farm.test.web;

import com.farm.test.config.auth.LoginUser;
import com.farm.test.config.auth.dto.SessionUser;
import com.farm.test.service.posts.PostsService;
import com.farm.test.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class BoardController {
  private final PostsService postsService;
  private final HttpSession httpSession;

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/board")
  public String board(Model model, @LoginUser SessionUser user) {
    model.addAttribute("posts", postsService.findAllDesc());

//    SessionUser user = (SessionUser) httpSession.getAttribute("user");

    if (user != null) {
      model.addAttribute("loginUserName", user.getName());
    }

    return "board";
  }

  @GetMapping("/posts/save")
  public String postsSave() {
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model) {
    PostsResponseDto postsResponseDto = postsService.findById(id);
    model.addAttribute("post", postsResponseDto);
    return "posts-update";
  }
}