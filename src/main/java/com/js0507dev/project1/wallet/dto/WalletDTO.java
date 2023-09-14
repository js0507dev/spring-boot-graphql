package com.js0507dev.project1.wallet.dto;

import com.js0507dev.project1.wallet.entity.Wallet;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WalletDTO {
    private Long id;
    private Long memberId;
    private String ticker;
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
