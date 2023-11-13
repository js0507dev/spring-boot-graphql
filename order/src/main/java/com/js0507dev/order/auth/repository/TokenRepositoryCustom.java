package com.js0507dev.order.auth.repository;

import com.js0507dev.order.auth.entity.Token;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.js0507dev.order.auth.entity.QToken.token;
import static com.js0507dev.order.member.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class TokenRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public Optional<Token> findByEmail(String email) {
    return Optional.ofNullable(queryFactory
        .from(token)
        .innerJoin(token.member, member)
        .fetchJoin()
        .where(member.email.eq(email))
        .select(token)
        .fetchOne());
  }
}
