package com.js0507dev.project1.wallet.repository;

import com.js0507dev.project1.wallet.entity.QWallet;
import com.js0507dev.project1.wallet.enums.Ticker;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WalletRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public Long create(Long memberId, Ticker ticker) {
    return queryFactory.insert(QWallet.wallet).columns(QWallet.wallet.memberId, QWallet.wallet.ticker).values(memberId, ticker).execute();
  }
}
