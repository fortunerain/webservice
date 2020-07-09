package com.farm.test.config;

import com.farm.test.config.auth.LoginUserArgumentResolver;
import com.farm.test.interceptor.HandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
  private final LoginUserArgumentResolver loginUserArgumentResolver;
  private final HandlerInterceptor handlerInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(handlerInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/");
  }

  // 파라미터에 @LoginUser 어노테이션으로 세션정보 전달하는 방법 대신 interceptor 로 구현함.
//  @Override
//  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//    resolvers.add(loginUserArgumentResolver);
//  }
}
