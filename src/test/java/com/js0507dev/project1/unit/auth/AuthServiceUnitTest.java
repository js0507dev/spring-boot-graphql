package com.js0507dev.project1.unit.auth;

import com.github.javafaker.Faker;
import com.js0507dev.project1.auth.dto.LoginDTO;
import com.js0507dev.project1.auth.entity.Token;
import com.js0507dev.project1.auth.repository.TokenRepository;
import com.js0507dev.project1.auth.service.AuthService;
import com.js0507dev.project1.auth.util.JwtUtil;
import com.js0507dev.project1.unit.helper.MemberMockFactory;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthServiceUnitTest {
  @Mock
  private TokenRepository tokenRepository;
  @Mock
  private MemberRepository memberRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Spy
  private JwtUtil jwtUtil = new JwtUtil();
  @InjectMocks
  private AuthService authService;
  private final Faker faker = new Faker();
  private final MemberMockFactory memberMockFactory = new MemberMockFactory(this.faker);

  @BeforeEach
  public void setup() {
    // TODO: application.yaml 파일에서 읽어오도록 변경
    ReflectionTestUtils.setField(jwtUtil, "secretKey", "R4GJibxmN7x0pvKZjNVA8CxQELk0YLuPrGMahj7iZy0LmEPnbgUUg68FugpHChTE");
    ReflectionTestUtils.setField(jwtUtil, "jwtExpiration", 86400000);
    ReflectionTestUtils.setField(jwtUtil, "refreshExpiration", 604800000);
  }

  @DisplayName("로그인 메서드 실행시 jwt를 생성하고, token table에 저장한다.")
  @Test
  public void givenFakes_whenLogin_thenReturnLoginPayloadDTOWithJWT() {
    Member mockMember = memberMockFactory.makeMock();
    LoginDTO dto = LoginDTO
        .builder()
        .email(mockMember.getEmail())
        .password(mockMember.getPassword())
        .build();
    when(memberRepository.findByEmail(anyString())).thenReturn(Optional.of(mockMember));
    when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
    when(tokenRepository.save(any(Token.class))).thenReturn(Mockito.mock(Token.class));

    Token createdToken = authService.login(dto);

    Mockito.verify(memberRepository, Mockito.times(1)).findByEmail(dto.getEmail());
    Mockito.verify(tokenRepository, Mockito.times(1)).save(any(Token.class));
    Assertions.assertNotNull(createdToken);
  }
}
