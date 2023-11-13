package com.js0507dev.wallet.wallet.repository;

import com.js0507dev.wallet.wallet.entity.QWallet;
import com.js0507dev.wallet.wallet.enums.Ticker;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WalletRepositoryCustom {
  private final JPAQueryFactory queryFactory;

  public Long create(Long memberId, Ticker ticker) {
    return queryFactory
        .insert(QWallet.wallet)
        .columns(QWallet.wallet.memberId, QWallet.wallet.ticker)
        .values(memberId, ticker.toString())
        .execute();
  }
}
