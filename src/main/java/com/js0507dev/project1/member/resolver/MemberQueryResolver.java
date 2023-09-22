package com.js0507dev.project1.member.resolver;

import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberQueryResolver {
    private final MemberService memberService;

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public MemberDTO member(@Argument Long id) {
        return memberService.findById(id);
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public List<MemberDTO> members() {
        return memberService.findAll();
    }
}
