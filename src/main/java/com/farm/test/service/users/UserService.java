package com.farm.test.service.users;

import com.farm.test.domain.user.Role;
import com.farm.test.domain.user.User;
import com.farm.test.domain.user.UserRepository;
import com.farm.test.web.dto.UserListResponseDto;
import com.farm.test.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;

  @Transactional
  public Long update(Long id, Role role) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저는 없습니다. id=" + id));

    user.update(role);
    return id;
  }

  @Transactional(readOnly = true)
  public List<UserListResponseDto> findAllUser() {
    return userRepository.findAllUsers().stream()
            .map(UserListResponseDto::new)
            .collect(Collectors.toList());
  }

  public UserResponseDto findById(Long id) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));
    return new UserResponseDto(user);
  }
}
