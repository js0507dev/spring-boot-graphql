package com.js0507dev.project1.member.dto;

import com.js0507dev.project1.member.entity.Member;
import lombok.Data;

@Data
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
