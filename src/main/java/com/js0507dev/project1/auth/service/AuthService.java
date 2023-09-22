package com.js0507dev.project1.auth.service;

import com.js0507dev.project1.auth.dto.LoginDTO;
import com.js0507dev.project1.auth.dto.LoginPayloadDTO;
import com.js0507dev.project1.auth.dto.TokenDTO;
import com.js0507dev.project1.auth.entity.Token;
import com.js0507dev.project1.auth.repository.TokenRepository;
import com.js0507dev.project1.auth.util.JwtUtil;
import com.js0507dev.project1.common.exception.NotFoundException;
import com.js0507dev.project1.common.exception.enums.ErrorCode;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final MemberRepository memberRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public LoginPayloadDTO login(LoginDTO dto) {
    Member member = memberRepository
        .findByEmail(dto.getEmail())
        .filter(entity -> passwordEncoder.matches(dto.getPassword(), entity.getPassword()))
        .orElseThrow(() -> new NotFoundException(ErrorCode.INVALID_LOGIN_INFO, "not matched login info"));
    Token createToken = generateTokenByMember(member);
    Token created = tokenRepository.save(createToken);

    return LoginPayloadDTO
        .builder()
        .token(TokenDTO.fromEntity(created))
        .build();
  }

  private Token generateTokenByMember(Member member) {
    String jwt = jwtUtil.generateJwt(member.getEmail());
    return Token
        .builder()
        .jwt(jwt)
        .member(member)
        .build();
  }
}
