package com.farm.test.interceptor;

import com.farm.test.config.auth.dto.SessionUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class HandlerInterceptor extends HandlerInterceptorAdapter {

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    HttpSession session = request.getSession();
    SessionUser user = (SessionUser) session.getAttribute("user");
    if (modelAndView != null && user != null) {
      modelAndView.addObject("loginUserName", user.getName());
    }
    super.postHandle(request, response, handler, modelAndView);
  }

}
