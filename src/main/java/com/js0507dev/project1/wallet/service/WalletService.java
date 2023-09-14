package com.js0507dev.project1.wallet.service;

import com.js0507dev.project1.wallet.dto.WalletDTO;
import com.js0507dev.project1.wallet.entity.Wallet;
import com.js0507dev.project1.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletDTO findById(Long id) {
        Wallet found = walletRepository.findById(id).orElseThrow();
        return WalletDTO.fromEntity(found);
    }

}
