package com.js0507dev.project1.wallet.service;

import com.js0507dev.project1.common.exception.*;
import com.js0507dev.project1.common.exception.enums.ErrorCode;
import com.js0507dev.project1.member.service.MemberFetchService;
import com.js0507dev.project1.wallet.dto.CreateWalletPayloadDTO;
import com.js0507dev.project1.wallet.dto.WalletDTO;
import com.js0507dev.project1.wallet.entity.Wallet;
import com.js0507dev.project1.wallet.enums.Ticker;
import com.js0507dev.project1.wallet.repository.WalletRepository;
import com.js0507dev.project1.wallet.repository.WalletRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletRepositoryCustom walletRepositoryCustom;
    private final MemberFetchService memberFetchService;

    public WalletDTO findById(Long id) {
        Wallet found = walletRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_WALLET, "not found wallet %d".formatted(id)));
        return WalletDTO.fromEntity(found);
    }

    public CreateWalletPayloadDTO create(Long memberId, Ticker ticker) {
        Long createdId = walletRepositoryCustom.create(memberId, ticker);
        Wallet created = walletRepository.findById(createdId).orElseThrow(() -> new InternalServerErrorException(ErrorCode.CREATE_WALLET_FAILED, "wallet creation failed"));
        return CreateWalletPayloadDTO.fromWalletDTO(WalletDTO.fromEntity(created));
    }
}
