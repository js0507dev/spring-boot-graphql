package com.js0507dev.wallet.wallet.repository;

import com.js0507dev.wallet.wallet.entity.Wallet;
import com.js0507dev.wallet.wallet.enums.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>, QuerydslPredicateExecutor<Wallet> {
  Optional<Wallet> findByMemberIdAndTicker(Long memberId, Ticker ticker);
}
