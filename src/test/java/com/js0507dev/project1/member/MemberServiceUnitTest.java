package com.js0507dev.project1.member;

import com.github.javafaker.Faker;
import com.js0507dev.project1.helper.MemberMockFactory;
import com.js0507dev.project1.member.dto.CreateMemberDTO;
import com.js0507dev.project1.member.dto.CreateMemberPayloadDTO;
import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.repository.MemberRepository;
import com.js0507dev.project1.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceUnitTest {
  @Mock
  private MemberRepository memberRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @InjectMocks
  private MemberService memberService;
  private final Faker faker = new Faker(Locale.KOREA);
  private final MemberMockFactory memberMockFactory = new MemberMockFactory(this.faker);

  @DisplayName("id를 입력해 findById 메서드를 호출하면 MemberDTO를 리턴한다.")
  @Test
  public void givenRandomId_whenFindById_thenReturnMemberDTO() {
    Long fakeId = faker
        .number()
        .randomNumber();
    Member mockMember = Mockito.mock(Member.class);
    when(memberRepository.findById(fakeId)).thenReturn(Optional.of(mockMember));

    MemberDTO result = memberService.findById(fakeId);
    assertEquals(mockMember.getId(), result.getId());
    assertEquals(mockMember.getName(), result.getName());
    assertEquals(mockMember.getEmail(), result.getEmail());
  }

  @DisplayName("findAll 메서드를 호출하면 MemberDTO 목록을 리턴한다.")
  @Test
  public void given_whenFindAll_thenReturnMemberDTOList() {
    List<Member> mockMembers = new ArrayList<>();
    mockMembers.add(Mockito.mock(Member.class));
    when(memberRepository.findAll()).thenReturn(mockMembers);

    List<MemberDTO> result = memberService.findAll();
    assertEquals(1, result.size());
    Member mockMember = mockMembers.get(0);
    MemberDTO resultDTO = result.get(0);
    assertEquals(mockMember.getId(), resultDTO.getId());
    assertEquals(mockMember.getName(), resultDTO.getName());
    assertEquals(mockMember.getEmail(), resultDTO.getEmail());
  }

  @DisplayName("create 메서드를 호출하면 Member를 저장하고, MemberDTO를 리턴한다.")
  @Test
  public void givenMockDto_whenCreate_thenSaveMemberAndReturnMemberDTO() {
    CreateMemberDTO dto = CreateMemberDTO
        .builder()
        .email(faker
            .internet()
            .emailAddress())
        .name(faker
            .name()
            .fullName())
        .password(faker
            .internet()
            .password())
        .build();
    String encryptedPassword = faker
        .crypto()
        .sha256();
    Member createdMember = Member
        .builder()
        .id(faker
            .number()
            .randomNumber())
        .name(dto.getName())
        .email(dto.getEmail())
        .password(encryptedPassword)
        .build();
    when(passwordEncoder.encode(anyString())).thenReturn(encryptedPassword);
    when(memberRepository.save(any())).thenReturn(createdMember);

    CreateMemberPayloadDTO created = memberService.create(dto);

    Mockito
        .verify(memberRepository, Mockito.times(1))
        .save(any());
    Mockito
        .verify(passwordEncoder, Mockito.times(1))
        .encode(anyString());
    assertNotNull(created.getRecord());
    MemberDTO memberDTO = created.getRecord();
    assertEquals(createdMember.getId(), memberDTO.getId());
    assertEquals(createdMember.getName(), memberDTO.getName());
    assertEquals(createdMember.getEmail(), memberDTO.getEmail());
  }
}
