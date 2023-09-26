package com.js0507dev.project1.member;

import com.github.javafaker.Faker;
import com.js0507dev.project1.auth.dto.LoginDTO;
import com.js0507dev.project1.auth.dto.LoginPayloadDTO;
import com.js0507dev.project1.auth.entity.Token;
import com.js0507dev.project1.auth.resolver.AuthMutationResolver;
import com.js0507dev.project1.auth.service.AuthService;
import com.js0507dev.project1.helper.AuthMockFactory;
import com.js0507dev.project1.helper.MemberMockFactory;
import com.js0507dev.project1.member.dto.CreateMemberDTO;
import com.js0507dev.project1.member.dto.CreateMemberPayloadDTO;
import com.js0507dev.project1.member.resolver.MemberMutationResolver;
import com.js0507dev.project1.member.service.MemberService;
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
    when(memberService.create(dto)).thenReturn(Mockito.mock(CreateMemberPayloadDTO.class));

    memberMutationResolver.create(dto);

    Mockito
        .verify(memberService, Mockito.times(1))
        .create(dto);
  }
}
