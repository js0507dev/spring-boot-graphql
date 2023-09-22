package com.js0507dev.project1.auth.resolver;

import com.js0507dev.project1.auth.dto.LoginDTO;
import com.js0507dev.project1.auth.dto.LoginPayloadDTO;
import com.js0507dev.project1.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AuthMutationResolver {
  private final AuthService authService;

  @MutationMapping
  public LoginPayloadDTO login(@Argument("dto") LoginDTO dto) {
    return authService.login(dto);
  }
}