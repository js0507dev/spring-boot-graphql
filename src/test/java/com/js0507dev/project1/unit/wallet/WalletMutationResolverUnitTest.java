package com.js0507dev.project1.unit.wallet;

import com.github.javafaker.Faker;
import com.js0507dev.project1.wallet.dto.CreateWalletPayloadDTO;
import com.js0507dev.project1.wallet.entity.Wallet;
import com.js0507dev.project1.wallet.enums.Ticker;
import com.js0507dev.project1.wallet.resolver.WalletMutationResolver;
import com.js0507dev.project1.wallet.service.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WalletMutationResolverUnitTest {
  @Mock
  private WalletService walletService;
  @InjectMocks
  private WalletMutationResolver walletMutationResolver;
  private Faker faker = new Faker();

  @DisplayName("지갑 생성 뮤테이션 실행시 원화 지갑이 생성해서 반환한다.")
  @Test
  public void givenMemberId_whenCreateWallet_thenReturnCreatedKrwWallet() {
    Long memberId = faker
        .number()
        .randomNumber();
    Wallet mockWallet = mock(Wallet.class);
    when(walletService.create(anyLong(), any())).thenReturn(mockWallet);

    CreateWalletPayloadDTO created = walletMutationResolver.createWallet(memberId);

    Mockito
        .verify(walletService, Mockito.times(1))
        .create(memberId, Ticker.KRW);
    Assertions.assertNotNull(created);
    Assertions.assertNotNull(created.getRecord());
  }
}
