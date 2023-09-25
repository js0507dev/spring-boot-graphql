package com.js0507dev.project1.helper;

import com.github.javafaker.Faker;
import com.js0507dev.project1.member.entity.Member;

public class MemberMockFactory {
  private Faker faker;

  public MemberMockFactory(Faker faker) {
    this.faker = faker;
  }

  public Member makeMock() {
    return Member
        .builder()
        .id(faker.number().randomNumber())
        .email(faker.internet().emailAddress())
        .name(faker.name().name())
        .password(faker.internet().password())
        .build();
  }
}
