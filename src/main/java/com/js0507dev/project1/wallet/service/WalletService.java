package com.js0507dev.project1.wallet.service;

import com.js0507dev.project1.common.exception.BadRequestException;
import com.js0507dev.project1.common.exception.InternalServerErrorException;
import com.js0507dev.project1.common.exception.NotFoundException;
import com.js0507dev.project1.common.exception.enums.ErrorCode;
import com.js0507dev.project1.member.service.MemberFetchService;
import com.js0507dev.project1.wallet.entity.Wallet;
import com.js0507dev.project1.wallet.enums.Ticker;
import com.js0507dev.project1.wallet.repository.WalletRepository;
import com.js0507dev.project1.wallet.repository.WalletRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WalletService {
  private final WalletRepository walletRepository;
  private final WalletRepositoryCustom walletRepositoryCustom;
  private final MemberFetchService memberFetchService;

  public Wallet findById(Long id) {
    return walletRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_WALLET, "not found wallet %d".formatted(id)));
  }

  public Wallet create(Long memberId, Ticker ticker) {
    Optional<Wallet> found = walletRepository.findByMemberIdAndTicker(memberId, ticker);
    if (found.isPresent()) {
      throw new BadRequestException(ErrorCode.ALREADY_CREATED_WALLET, "already created krw " + "wallet");
    }
    Long createdId = walletRepositoryCustom.create(memberId, ticker);
    return walletRepository
        .findById(createdId)
        .orElseThrow(() -> new InternalServerErrorException(ErrorCode.CREATE_WALLET_FAILED, "wallet creation failed"));
  }
}
