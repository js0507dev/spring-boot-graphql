package com.js0507dev.wallet.auth.dto;

import com.js0507dev.wallet.auth.entity.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
  private String jwt;
  private Long memberId;
  private Boolean revoked;
  private String revokedReason;

  public static TokenDTO fromEntity(Token entity) {
    return TokenDTO.builder().jwt(entity.getJwt()).memberId(entity.getMemberId()).revoked(entity.getRevoked()).revokedReason(entity.getRevokeReason()).build();
  }
}
