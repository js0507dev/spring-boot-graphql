package com.js0507dev.project1.wallet.service;

import com.js0507dev.project1.member.dto.MemberDTO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WalletService {
    private final WalletRepository walletRepository;
    private final WalletRepositoryCustom walletRepositoryCustom;
    private final MemberFetchService memberFetchService;

    public WalletDTO findById(Long id) {
        Wallet found = walletRepository.findById(id).orElseThrow();
        return WalletDTO.fromEntity(found);
    }

    public CreateWalletPayloadDTO create(Long memberId, Ticker ticker) {
        Long createdId = walletRepositoryCustom.create(memberId, ticker);
        Wallet created = walletRepository.findById(createdId).orElseThrow();
        return CreateWalletPayloadDTO.fromWalletDTO(WalletDTO.fromEntity(created));
    }

    public Map<WalletDTO, MemberDTO> fetchMemberByWallet(List<WalletDTO> wallets) {
        return null;
    }
}
