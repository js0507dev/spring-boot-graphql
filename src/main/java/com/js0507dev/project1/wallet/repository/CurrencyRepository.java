package com.js0507dev.project1.wallet.repository;

import com.js0507dev.project1.wallet.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
