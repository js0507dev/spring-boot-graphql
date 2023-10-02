package com.js0507dev.project1.unit.wallet;

import com.github.javafaker.Faker;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.service.MemberService;
import com.js0507dev.project1.wallet.dto.WalletDTO;
import com.js0507dev.project1.wallet.entity.Wallet;
import com.js0507dev.project1.wallet.resolver.WalletQueryResolver;
import com.js0507dev.project1.wallet.service.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WalletQueryResolverUnitTest {
  @Mock
  private WalletService walletService;
  @Mock
  private MemberService memberService;
  @InjectMocks
  private WalletQueryResolver walletQueryResolver;
  private Faker faker = new Faker();

  @DisplayName("지갑 단건조회 쿼리 실행시 WalletService.findById 메서드가 1번 호출된다.")
  @Test
  public void givenWalletId_whenQueryWallet_thenReturnWalletDTO() {
    Long walletId = faker
        .number()
        .randomNumber();
    Wallet mockWallet = mock(Wallet.class);
    when(walletService.findById(anyLong())).thenReturn(mockWallet);

    WalletDTO found = walletQueryResolver.queryWallet(walletId);

    Mockito
        .verify(walletService, Mockito.times(1))
        .findById(walletId);
    Assertions.assertEquals(mockWallet.getId(), found.getId());
    Assertions.assertEquals(mockWallet.getMemberId(), found.getMemberId());
    Assertions.assertEquals(mockWallet.getTicker(), found.getTicker());
    Assertions.assertEquals(mockWallet.getTotalBalance(), found.getTotalBalance());
    Assertions.assertEquals(mockWallet.getAvailableBalance(), found.getAvailableBalance());
    Assertions.assertEquals(mockWallet.getLockedAmount(), found.getLockedAmount());
  }

  @DisplayName("지갑의 사용자를 조회하면 MemberService.findById를 1번 호출한다.")
  @Test
  public void givenWalletDTO_whenFieldMember_thenReturnMemberDTO() {
    WalletDTO mockWalletDTO = mock(WalletDTO.class);
    Member mockMember = mock(Member.class);
    when(memberService.findById(anyLong())).thenReturn(mockMember);

    walletQueryResolver.fieldMember(mockWalletDTO);

    Mockito
        .verify(memberService, Mockito.times(1))
        .findById(mockWalletDTO.getMemberId());
  }
}
