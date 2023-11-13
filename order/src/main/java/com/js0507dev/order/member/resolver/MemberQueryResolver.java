package com.js0507dev.order.member.resolver;

import com.js0507dev.order.member.dto.MemberDTO;
import com.js0507dev.order.member.entity.Member;
import com.js0507dev.order.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberQueryResolver {
  private final MemberService memberService;

  @QueryMapping
  @PreAuthorize("isAuthenticated()")
  public MemberDTO member(@Argument Long id) {
    Member found = memberService.findById(id);
    return MemberDTO.fromEntity(found);
  }

  @QueryMapping
  @PreAuthorize("isAuthenticated()")
  public List<MemberDTO> members() {
    return memberService.findAll().stream().map(MemberDTO::fromEntity).collect(Collectors.toList());
  }
}
