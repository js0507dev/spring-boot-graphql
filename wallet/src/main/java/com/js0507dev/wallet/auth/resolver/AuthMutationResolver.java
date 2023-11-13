package com.js0507dev.wallet.auth.resolver;

import com.js0507dev.wallet.auth.dto.LoginDTO;
import com.js0507dev.wallet.auth.dto.LoginPayloadDTO;
import com.js0507dev.wallet.auth.dto.TokenDTO;
import com.js0507dev.wallet.auth.entity.Token;
import com.js0507dev.wallet.auth.service.AuthService;
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
    Token createdToken = authService.login(dto);

    return LoginPayloadDTO
        .builder()
        .token(TokenDTO.fromEntity(createdToken))
        .build();
  }
}
