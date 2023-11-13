package com.js0507dev.wallet.auth.repository;

import com.js0507dev.wallet.auth.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
}
