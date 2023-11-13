package com.js0507dev.wallet.wallet.dto;

import com.js0507dev.wallet.wallet.entity.Wallet;
import com.js0507dev.wallet.wallet.enums.Ticker;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletDTO {
    private Long id;
    private Long memberId;
    private Ticker ticker;
    private Long totalBalance;
    private Long availableBalance;
    private Long lockedAmount;

    public static WalletDTO fromEntity(Wallet entity) {
        return WalletDTO.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .ticker(entity.getTicker())
                .totalBalance(entity.getTotalBalance())
                .availableBalance(entity.getAvailableBalance())
                .lockedAmount(entity.getLockedAmount())
                .build();
    }
}
