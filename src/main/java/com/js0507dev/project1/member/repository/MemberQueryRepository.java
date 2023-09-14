package com.js0507dev.project1.member.repository;

import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Member findByIdWithWallets(Long id) {
        return queryFactory.selectFrom(QMember.member).where(QMember.member.id.eq(id)).fetchOne();
    }
}
