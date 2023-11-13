package com.js0507dev.order.auth.service;

import com.js0507dev.order.auth.dto.LoginDTO;
import com.js0507dev.order.auth.entity.Token;
import com.js0507dev.order.auth.util.JwtUtil;
import com.js0507dev.order.member.entity.Member;
import com.js0507dev.order.member.repository.MemberRepository;
import com.js0507dev.order.auth.repository.TokenRepository;
import com.js0507dev.order.common.exception.NotFoundException;
import com.js0507dev.order.common.exception.enums.ErrorCode;
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

  public Token login(LoginDTO dto) {
    Member member = memberRepository
        .findByEmail(dto.getEmail())
        .filter(entity -> passwordEncoder.matches(dto.getPassword(), entity.getPassword()))
        .orElseThrow(() -> new NotFoundException(ErrorCode.INVALID_LOGIN_INFO, "not matched login info"));
    Token createToken = generateTokenByMember(member);
    return tokenRepository.save(createToken);
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
