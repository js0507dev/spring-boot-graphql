package com.js0507dev.wallet.member.service;

import com.js0507dev.wallet.member.dto.MemberDTO;
import com.js0507dev.wallet.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberFetchService {
  private final MemberRepository memberRepository;

  public List<MemberDTO> findByIds(List<Long> ids) {
    return memberRepository.findAllById(ids).stream().map(MemberDTO::fromEntity).toList();
  }
}
