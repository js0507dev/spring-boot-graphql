package com.js0507dev.order.unit.auth;

import com.github.javafaker.Faker;
import com.js0507dev.order.auth.dto.LoginDTO;
import com.js0507dev.order.auth.dto.LoginPayloadDTO;
import com.js0507dev.order.auth.dto.TokenDTO;
import com.js0507dev.order.auth.entity.Token;
import com.js0507dev.order.auth.resolver.AuthMutationResolver;
import com.js0507dev.order.auth.service.AuthService;
import com.js0507dev.order.unit.helper.AuthMockFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthMutationResolverUnitTest {
  @Mock
  private AuthService authService;
  @InjectMocks
  private AuthMutationResolver authMutationResolver;
  private final Faker faker = new Faker();
  private final AuthMockFactory authMockFactory = new AuthMockFactory();

  @DisplayName("로그인 뮤테이션 실행시 AuthService.login 메서드가 1번 호출된다.")
  @Test
  public void givenFakes_whenLogin_thenAuthServiceCalledWith1Times() {
    LoginDTO dto = LoginDTO
        .builder()
        .email(faker
            .internet()
            .emailAddress())
        .password(faker
            .internet()
            .password())
        .build();
    Token token = authMockFactory.mockToken();
    when(authService.login(dto)).thenReturn(token);

    LoginPayloadDTO createdToken = authMutationResolver.login(dto);

    Mockito
        .verify(authService, Mockito.times(1))
        .login(dto);
    TokenDTO record = createdToken.getToken();
    Assertions.assertEquals(token.getJwt(), record.getJwt());
    Assertions.assertEquals(token.getMemberId(), record.getMemberId());
    Assertions.assertEquals(token.getRevoked(), record.getRevoked());
    Assertions.assertEquals(token.getRevokeReason(), record.getRevokedReason());
  }
}
