package com.js0507dev.project1.member.service;

import com.js0507dev.project1.member.dto.CreateMemberDTO;
import com.js0507dev.project1.member.dto.CreateMemberPayloadDTO;
import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.repository.MemberQueryRepository;
import com.js0507dev.project1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
  private final MemberRepository memberRepository;
  private final MemberQueryRepository memberQueryRepository;

  public MemberDTO findById(Long id) {
    Member found = memberRepository.findById(id).orElseThrow();
    return MemberDTO.fromEntity(found);
  }

  public List<MemberDTO> findAll() {
    return memberRepository.findAll().stream().map(MemberDTO::fromEntity).toList();
  }

  public CreateMemberPayloadDTO create(CreateMemberDTO dto) {
    Member created = memberRepository.save(dto.toEntity());
    MemberDTO found = this.findById(created.getId());

    return CreateMemberPayloadDTO.fromMemberDTO(found);
  }
}
