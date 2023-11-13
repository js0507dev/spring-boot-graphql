package com.js0507dev.wallet.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMemberPayloadDTO {
    private MemberDTO record;

    public static CreateMemberPayloadDTO fromMemberDTO(MemberDTO record) {
        return CreateMemberPayloadDTO.builder().record(record).build();
    }
}
