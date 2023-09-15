package com.js0507dev.project1.wallet.service;

import com.js0507dev.project1.member.entity.Member;
import com.js0507dev.project1.wallet.dto.CreateWalletPayloadDTO;
import com.js0507dev.project1.wallet.dto.WalletDTO;
import com.js0507dev.project1.wallet.entity.Wallet;
import com.js0507dev.project1.wallet.enums.Ticker;
import com.js0507dev.project1.wallet.repository.WalletRepository;
import com.js0507dev.project1.wallet.repository.WalletRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletRepositoryCustom walletRepositoryCustom;

    public WalletDTO findById(Long id) {
        Wallet found = walletRepository.findById(id).orElseThrow();
        return WalletDTO.fromEntity(found);
    }

    public CreateWalletPayloadDTO create(Long memberId, Ticker ticker) {
        Long createdId = walletRepositoryCustom.create(memberId, ticker);
        Wallet created = walletRepository.findById(createdId).orElseThrow();
        return CreateWalletPayloadDTO.fromWalletDTO(WalletDTO.fromEntity(created));
    }

}
