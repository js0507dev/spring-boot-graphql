package com.js0507dev.project1.member.service;

import com.js0507dev.project1.auth.dto.LoginPayloadDTO;
import com.js0507dev.project1.common.exception.NotFoundException;
import com.js0507dev.project1.common.exception.enums.ErrorCode;
import com.js0507dev.project1.member.dto.CreateMemberDTO;
import com.js0507dev.project1.member.dto.CreateMemberPayloadDTO;
import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.repository.MemberRepository;
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

  public MemberDTO findById(Long id) {
    Member found = memberRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER, "not found member by %d".formatted(id)));
    return MemberDTO.fromEntity(found);
  }

  public List<MemberDTO> findAll() {
    return memberRepository.findAll().stream().map(MemberDTO::fromEntity).toList();
  }

  public CreateMemberPayloadDTO create(CreateMemberDTO dto) {
    dto.setPassword(passwordEncoder.encode(dto.getPassword()));
    Member created = memberRepository.save(dto.toEntity());
    MemberDTO responseDto = MemberDTO.fromEntity(created);

    return CreateMemberPayloadDTO.fromMemberDTO(responseDto);
  }
}
