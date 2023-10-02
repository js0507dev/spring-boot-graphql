package com.js0507dev.project1.unit.member;

import com.github.javafaker.Faker;
import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.resolver.MemberQueryResolver;
import com.js0507dev.project1.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberQueryResolverUnitTest {
  @Mock
  private MemberService memberService;
  @InjectMocks
  private MemberQueryResolver memberQueryResolver;
  private final Faker faker = new Faker();

  @DisplayName("멤버 단건조회 쿼리 실행시 MemberService.findById 메서드가 1번 호출된다.")
  @Test
  public void givenFakes_whenFindMember_thenMemberServiceCalledWith1Times() {
    Long id = faker
        .number()
        .randomNumber();
    Member mockMember = Mockito.mock(Member.class);
    when(memberService.findById(id)).thenReturn(mockMember);

    MemberDTO result = memberQueryResolver.member(id);

    Mockito
        .verify(memberService, Mockito.times(1))
        .findById(id);
    assertEquals(mockMember.getId(), result.getId());
    assertEquals(mockMember.getName(), result.getName());
    assertEquals(mockMember.getEmail(), result.getEmail());
  }

  @DisplayName("멤버 다건조회 쿼리 실행시 MemberService.findAll 메서드가 1번 호출된다.")
  @Test
  public void givenFakes_whenFindMembers_thenMemberServiceCalledWith1Times() {
    List<Member> mockMember = IntStream
        .range(1, 2)
        .mapToObj(value -> Mockito.mock(Member.class))
        .toList();
    when(memberService.findAll()).thenReturn(mockMember);

    List<MemberDTO> result = memberQueryResolver.members();

    Mockito
        .verify(memberService, Mockito.times(1))
        .findAll();
    assertEquals(mockMember.size(), result.size());
  }
}
