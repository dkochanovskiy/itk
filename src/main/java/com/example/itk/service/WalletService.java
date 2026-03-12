package com.example.itk.service;

import com.example.itk.dto.OperationType;
import com.example.itk.dto.WalletRequest;
import com.example.itk.dto.WalletResponse;
import com.example.itk.entity.Wallet;
import com.example.itk.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public WalletResponse save(WalletRequest request) {

        Wallet wallet = new Wallet();
        wallet.setWalletId(request.getWalletId());
        wallet.setOperationType(request.getOperationType());
        wallet.setAmount(request.getAmount());

        walletRepository.save(wallet);

        return new WalletResponse(request.getWalletId(), request.getOperationType(), request.getAmount());
    }

    public Integer getBalanceByWalletId(UUID walletId) {

        return walletRepository.getBalanceByWalletId(walletId);
    }
}
