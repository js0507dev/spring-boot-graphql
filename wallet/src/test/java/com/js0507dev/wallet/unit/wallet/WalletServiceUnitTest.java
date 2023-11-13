package com.js0507dev.wallet.unit.wallet;

import com.github.javafaker.Faker;
import com.js0507dev.wallet.common.exception.BadRequestException;
import com.js0507dev.wallet.common.exception.CustomBaseException;
import com.js0507dev.wallet.common.exception.InternalServerErrorException;
import com.js0507dev.wallet.common.exception.NotFoundException;
import com.js0507dev.wallet.common.exception.enums.ErrorCode;
import com.js0507dev.wallet.member.entity.Member;
import com.js0507dev.wallet.wallet.entity.Wallet;
import com.js0507dev.wallet.wallet.enums.Ticker;
import com.js0507dev.wallet.wallet.repository.WalletRepository;
import com.js0507dev.wallet.wallet.repository.WalletRepositoryCustom;
import com.js0507dev.wallet.wallet.service.WalletService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WalletServiceUnitTest {
  @Mock
  private WalletRepository walletRepository;
  @Mock
  private WalletRepositoryCustom walletRepositoryCustom;
  @InjectMocks
  private WalletService walletService;
  private final Faker faker = new Faker(Locale.KOREA);

  @DisplayName("memberId 및 ticker를 입력하면 해당 사용자의 해당 ticker의 지갑이 생성되어 반환된다.")
  @Test
  public void givenMemberIdAndTicker_whenCreate_thenReturnCreatedKrwWallet() {
    Long fakeMemberId = faker
        .number()
        .randomNumber();
    Ticker ticker = Ticker.KRW;
    Member mockMember = mock(Member.class);
    mockMember.setId(fakeMemberId);
    Long createdWalletId = faker
        .number()
        .randomNumber();
    Optional<Wallet> createdWallet = Optional.of(Wallet
        .builder()
        .id(createdWalletId)
        .memberId(fakeMemberId)
        .member(mockMember)
        .ticker(ticker)
        .availableBalance(0L)
        .lockedAmount(0L)
        .totalBalance(0L)
        .build());
    when(walletRepository.findByMemberIdAndTicker(anyLong(), any())).thenReturn(Optional.empty());
    when(walletRepositoryCustom.create(anyLong(), any())).thenReturn(createdWalletId);
    when(walletRepository.findById(anyLong())).thenReturn(createdWallet);

    Wallet created = walletService.create(fakeMemberId, ticker);

    Mockito
        .verify(walletRepository, Mockito.times(1))
        .findByMemberIdAndTicker(fakeMemberId, ticker);
    Mockito
        .verify(walletRepository, Mockito.times(1))
        .findById(createdWalletId);
    Mockito
        .verify(walletRepositoryCustom, Mockito.times(1))
        .create(fakeMemberId, ticker);
    assertNotNull(created);
  }

  @DisplayName("이미 원화 지갑이 생성된 상태에서 원화 지갑 생성 호출시 BadRequest 오류 발생")
  @Test
  public void givenAlreadyCreatedTicker_whenCreate_thenThrowBadRequestException() {
    Long fakeMemberId = faker
        .number()
        .randomNumber();
    Ticker ticker = Ticker.KRW;
    Optional<Wallet> alreadyExistsWallet = Optional.of(mock(Wallet.class));
    when(walletRepository.findByMemberIdAndTicker(anyLong(), any())).thenReturn(alreadyExistsWallet);

    try {
      walletService.create(fakeMemberId, ticker);
      fail();
    } catch (Exception ex) {
      assertInstanceOf(BadRequestException.class, ex);
      CustomBaseException customBaseException = (CustomBaseException) ex;
      assertEquals(ErrorCode.ALREADY_CREATED_WALLET, customBaseException.getCode());
    }
  }

  @DisplayName("지갑 생성후 생성된 id로 find 실패시 내부 오류 발생")
  @Test
  public void given_whenCreate_thenThrowInternalServerErrorException() {
    Long fakeMemberId = faker
        .number()
        .randomNumber();
    Ticker ticker = Ticker.KRW;
    when(walletRepository.findByMemberIdAndTicker(anyLong(), any())).thenReturn(Optional.empty());
    when(walletRepositoryCustom.create(anyLong(), any())).thenReturn(faker
        .number()
        .randomNumber());
    when(walletRepository.findById(anyLong())).thenReturn(Optional.empty());

    try {
      walletService.create(fakeMemberId, ticker);
      fail();
    } catch (Exception ex) {
      assertInstanceOf(InternalServerErrorException.class, ex);
      CustomBaseException customBaseException = (CustomBaseException) ex;
      assertEquals(ErrorCode.CREATE_WALLET_FAILED, customBaseException.getCode());
    }
  }

  @DisplayName("id를 입력해 findById 메서드를 호출하면 Wallet을 리턴한다.")
  @Test
  public void givenWalletId_whenFindById_thenReturnFoundWallet() {
    Long fakeWalletId = faker
        .number()
        .randomNumber();
    Wallet mockWallet = mock(Wallet.class);
    when(walletRepository.findById(anyLong())).thenReturn(Optional.of(mockWallet));

    Wallet found = walletService.findById(fakeWalletId);
    Mockito
        .verify(walletRepository, Mockito.times(1))
        .findById(fakeWalletId);
    assertNotNull(found);
  }

  @DisplayName("존재하지 않는 wallet을 조회하면 NotFoundException을 던진다.")
  @Test
  public void givenInvalidWalletId_whenFindById_thenThrowNotFoundException() {
    Long fakeWalletId = faker
        .number()
        .randomNumber();
    when(walletRepository.findById(anyLong())).thenReturn(Optional.empty());

    try {
      walletService.findById(fakeWalletId);
      fail();
    } catch (Exception ex) {
      assertInstanceOf(NotFoundException.class, ex);
      CustomBaseException customBaseException = (CustomBaseException) ex;
      assertEquals(ErrorCode.NOT_FOUND_WALLET, customBaseException.getCode());
    }
  }
}
