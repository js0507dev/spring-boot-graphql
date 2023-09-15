package com.js0507dev.project1.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateWalletPayloadDTO {
  private WalletDTO record;

  public static CreateWalletPayloadDTO fromWalletDTO(WalletDTO record) {
    return CreateWalletPayloadDTO.builder().record(record).build();
  }
}
