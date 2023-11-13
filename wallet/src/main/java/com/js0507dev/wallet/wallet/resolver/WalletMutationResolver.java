package com.js0507dev.wallet.wallet.resolver;

import com.js0507dev.wallet.wallet.dto.CreateWalletPayloadDTO;
import com.js0507dev.wallet.wallet.dto.WalletDTO;
import com.js0507dev.wallet.wallet.entity.Wallet;
import com.js0507dev.wallet.wallet.enums.Ticker;
import com.js0507dev.wallet.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class WalletMutationResolver {
  private final WalletService walletService;

  @MutationMapping
  public CreateWalletPayloadDTO createWallet(@Argument Long memberId) {
    Wallet created = walletService.create(memberId, Ticker.KRW);
    return CreateWalletPayloadDTO.fromWalletDTO(WalletDTO.fromEntity(created));
  }
}
