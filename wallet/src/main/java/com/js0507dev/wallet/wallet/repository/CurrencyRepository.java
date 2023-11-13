package com.js0507dev.wallet.wallet.repository;

import com.js0507dev.wallet.wallet.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
