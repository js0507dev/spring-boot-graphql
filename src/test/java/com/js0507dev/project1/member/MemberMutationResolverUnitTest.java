package com.js0507dev.project1.member;

import com.js0507dev.project1.member.dto.CreateMemberDTO;
import com.js0507dev.project1.member.dto.CreateMemberPayloadDTO;
import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.resolver.MemberMutationResolver;
import com.js0507dev.project1.member.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberMutationResolverUnitTest {
  @Mock
  private MemberService memberService;
  @InjectMocks
  private MemberMutationResolver memberMutationResolver;

  @DisplayName("멤버 생성 뮤테이션 실행시 MemberService.create 메서드가 1번 호출된다.")
  @Test
  public void givenFakes_whenCreateMember_thenMemberServiceCalledWith1Times() {
    CreateMemberDTO dto = Mockito.mock(CreateMemberDTO.class);
    Member mockMember = Mockito.mock(Member.class);
    when(memberService.create(dto)).thenReturn(mockMember);

    CreateMemberPayloadDTO created = memberMutationResolver.create(dto);

    Mockito
        .verify(memberService, Mockito.times(1))
        .create(dto);
    MemberDTO record = created.getRecord();
    Assertions.assertNotNull(record);
    Assertions.assertEquals(mockMember.getId(), record.getId());
    Assertions.assertEquals(mockMember.getName(), record.getName());
    Assertions.assertEquals(mockMember.getEmail(), record.getEmail());
  }
}
