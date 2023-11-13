package com.js0507dev.order.member.dto;

import com.js0507dev.order.member.entity.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMemberDTO {
  private String name;
  private String email;
  private String password;

  public Member toEntity() {
    return Member
        .builder()
        .name(this.name)
        .email(this.email)
        .password(this.password)
        .build();
  }
}
