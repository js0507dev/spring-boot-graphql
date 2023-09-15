package com.js0507dev.project1.wallet.resolver;

import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.service.MemberService;
import com.js0507dev.project1.wallet.dto.WalletDTO;
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
    @QueryMapping
    public WalletDTO wallet(@Argument Long id) {
        return walletService.findById(id);
    }

    @SchemaMapping(typeName = "Wallet", field = "member")
    public MemberDTO member(WalletDTO parent) {
        if (parent != null) {
            return memberService.findById(parent.getMemberId());
        }
        return null;
    }
}
