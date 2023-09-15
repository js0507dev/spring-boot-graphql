package com.js0507dev.project1.member.repository;

import com.js0507dev.project1.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.js0507dev.project1.member.entity.QMember.member;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<Member> findByIdWithWallets(Long id) {
        return Optional.ofNullable(queryFactory.selectFrom(member).where(member.id.eq(id)).fetchOne());
    }
}
