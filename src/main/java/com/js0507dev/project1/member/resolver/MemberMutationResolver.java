package com.js0507dev.project1.member.resolver;

import com.js0507dev.project1.member.dto.CreateMemberDTO;
import com.js0507dev.project1.member.dto.CreateMemberPayloadDTO;
import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberMutationResolver {
  private final MemberService memberService;

  @MutationMapping(name = "createMember")
  public CreateMemberPayloadDTO create(@Argument CreateMemberDTO data) {
    Member created = memberService.create(data);
    MemberDTO responseDto = MemberDTO.fromEntity(created);
    return CreateMemberPayloadDTO.fromMemberDTO(responseDto);
  }
}
