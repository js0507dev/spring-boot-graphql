package com.js0507dev.order.member.service;

import com.js0507dev.order.member.dto.CreateMemberDTO;
import com.js0507dev.order.member.entity.Member;
import com.js0507dev.order.common.exception.NotFoundException;
import com.js0507dev.order.common.exception.enums.ErrorCode;
import com.js0507dev.order.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  public Member findById(Long id) {
    return memberRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER, "not foundember by %d".formatted(id)));
  }

  public Member findByEmail(String email) {
    return memberRepository
        .findByEmail(email)
        .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER,
            "not found member by %s".formatted(email)));
  }

  public List<Member> findAll() {
    return memberRepository.findAll();
  }

  public Member create(CreateMemberDTO dto) {
    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    return memberRepository.save(dto.toEntity());
  }
}
