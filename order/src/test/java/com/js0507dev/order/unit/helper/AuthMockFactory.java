package com.js0507dev.order.unit.helper;

import com.js0507dev.order.auth.entity.Token;
import com.js0507dev.order.member.entity.Member;
import org.mockito.Mockito;

import java.util.UUID;

public class AuthMockFactory {

  public Token mockToken() {
    return Token
        .builder()
        .jwt(UUID.randomUUID().toString())
        .member(Mockito.mock(Member.class))
        .revoked(false)
        .build();
  }
}
