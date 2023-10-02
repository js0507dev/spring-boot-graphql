package com.js0507dev.project1.wallet.resolver;

import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.service.MemberService;
import com.js0507dev.project1.wallet.dto.WalletDTO;
import com.js0507dev.project1.wallet.entity.Wallet;
import com.js0507dev.project1.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class WalletQueryResolver {
  private final WalletService walletService;
  private final MemberService memberService;

  @QueryMapping(name = "wallet")
  public WalletDTO queryWallet(@Argument Long id) {
    Wallet found = walletService.findById(id);
    return WalletDTO.fromEntity(found);
  }

  @SchemaMapping(typeName = "Wallet", field = "member")
  public MemberDTO fieldMember(WalletDTO parent) {
    Member found = memberService.findById(parent.getMemberId());
    return MemberDTO.fromEntity(found);
  }
}
