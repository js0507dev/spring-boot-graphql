package com.js0507dev.wallet.member.dto;

import com.js0507dev.wallet.member.entity.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
    private Long id;
    private String name;
    private String email;

    public static MemberDTO fromEntity(Member entity) {
        return MemberDTO.builder().id(entity.getId()).name(entity.getName()).email(entity.getEmail()).build();
    }
}
