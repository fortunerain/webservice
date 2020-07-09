package com.farm.test.web.controller;

import com.farm.test.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class DashBoardController {
  private final PostsService postsService;
  private final HttpSession httpSession;

  @GetMapping("/dashboard")
  public String dashboard(Model model) {
    return "dashboard";
  }
}
