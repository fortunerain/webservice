package com.farm.test.config.auth;

import com.farm.test.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final CustomOAuth2UserService customOauth2UserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      //크로스 사이트 요청 위조(Cross-site request forgery) 때문에 spring security 에서는 모든 기본 보안 헤더가 추가되는데 X-Frame-Options 옵션이 디폴트로 거부로 되어 있다.
      // TODO: 2020-06-27 자세한 내용 확인 필요.
      .csrf().disable().cors().disable().headers().frameOptions().disable()  //h2-console 화면을 사용하기 위해 해당 옵션을 disable 시킴. 403 에러 발생함.
      .and()
      .authorizeRequests()  // url별 권한 관리를 설정하는 옵션. 이게 있어야 antMatchers 옵션 사용가능함.
      .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
      .antMatchers("/api/v1/**").hasRole(Role.USER.name())
      .anyRequest().authenticated() // 위 설정된 url 제외한 나머지 request 들은 모두 로그인한 사용자만 허용함.
      .and()
      .logout().logoutSuccessUrl("/")
      .and()
      .oauth2Login()
      .defaultSuccessUrl("/board")
      .userInfoEndpoint()
      .userService(customOauth2UserService);  //소셜 로그인 성공 후 후속조치를 진행할 user service 인터페이스 구현체 등록.
  }
}
