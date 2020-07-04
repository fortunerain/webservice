package com.farm.test.service.users;

import com.farm.test.domain.posts.Posts;
import com.farm.test.domain.user.Role;
import com.farm.test.domain.user.User;
import com.farm.test.domain.user.UserRepository;
import com.farm.test.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;

  @Transactional
  public Long update(String email, Role role) {
    Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("해당 이메일이 없습니다. email=" + email)));

    // 트랜젝션안에서 db 데이터를 가져오게 되면 영속성 컨텍스트가 유지된상태.
    // 트랜젝션이 끝나는 시점에 db 변경함. Entity 객체의 값만 변경해주면됨. update 쿼리를 날릴 필요가 없다. -> 더티체킹이라고 함.
    user.get().update(role);
    return user.get().getId();
  }

  @Transactional(readOnly = true)
  public List<UserListResponseDto> findAllUser() {
    return userRepository.findAllUsers().stream()
            .map(UserListResponseDto::new)
            .collect(Collectors.toList());
  }

  public UserResponseDto findByEmail(String email) {
    Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. email=" + email)));
    return new UserResponseDto(user.get());
  }

  public UserResponseDto findById(Long id) {
    Optional<User> user = Optional.ofNullable(userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id)));
    return new UserResponseDto(user.get());
  }
}
