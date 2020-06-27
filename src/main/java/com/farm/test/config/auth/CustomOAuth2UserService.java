package com.farm.test.config.auth;

import com.farm.test.config.auth.dto.OAuthAttributes;
import com.farm.test.config.auth.dto.SessionUser;
import com.farm.test.domain.user.User;
import com.farm.test.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
  private final UserRepository userRepository;
  private final HttpSession httpSession;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    // 로그인 진행중인 서비스를 구분하는 코드. 현재는 구글 밖에 없지만 이후 네이버 연동시 구분하기 위함.
    // 구글 로그인시 "google" 값 들어옴.
    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    // "sub" 값 들어옴. oauth2 로그인 진행시 키가 되는 필드값. 구글의 경우 해당 값을 지원하지만 네이버 카카오등은 지원하지 않음.
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    OAuthAttributes authAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

    // 구글 사용자 정보가 변경되었을때를 대비하여 user 엔티티에 반영시킴.
    User user = saveOrUpdate(authAttributes);
    // 로그인 성공 후 세션에 user 라는 이름으로 SessionUser 정보 저장해둠.
    // 이후 화면에 user 이름 정보 노출할때 사용.
    httpSession.setAttribute("user", new SessionUser(user));

    return new DefaultOAuth2User(Collections.singleton(
      new SimpleGrantedAuthority(user.getRoleKey())),
      authAttributes.getAttributes(),
      authAttributes.getNameAttributeKey());

  }

  private User saveOrUpdate(OAuthAttributes authAttributes) {
    User user = userRepository.findByEmail(authAttributes.getEmail())
      .map(entity -> entity.update(authAttributes.getName(), authAttributes.getPicture()))
      .orElse(authAttributes.toEntity());

    return userRepository.save(user);
  }
}
