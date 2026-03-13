package com.example.itk.service;

import com.example.itk.dto.OperationType;
import com.example.itk.dto.WalletRequest;
import com.example.itk.dto.WalletResponse;
import com.example.itk.entity.Transaction;
import com.example.itk.entity.Wallet;
import com.example.itk.exception.InsufficientFundsException;
import com.example.itk.repository.TransactionRepository;
import com.example.itk.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository,
                         TransactionRepository transactionRepository) {

        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public WalletResponse save(WalletRequest request) {

        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseGet(() -> {
                    Wallet newWallet = new Wallet(request.getWalletId());
                    return walletRepository.save(newWallet);
                });

        if (wallet.getWalletId() == null) {
            throw new NoSuchElementException("Wallet not found");
        }

        int currentBalance = getBalanceByWalletId(request.getWalletId());

        if (request.getOperationType() == OperationType.WITHDRAW) {
            if (currentBalance < request.getAmount()) {
                throw new InsufficientFundsException("Insufficient funds for withdrawal");
            }
        }

        Transaction transaction = new Transaction(
                request.getWalletId(),
                request.getOperationType(),
                request.getAmount()
        );
        transactionRepository.save(transaction);

        int newBalance = getBalanceByWalletId(request.getWalletId());

        String message = request.getOperationType() == OperationType.DEPOSIT
                ? "Deposit successful"
                : "Withdrawal successful";

        return new WalletResponse(request.getWalletId(), newBalance, message);
    }

    public Integer getBalanceByWalletId(UUID walletId) {

        if (!walletRepository.existsById(walletId)) {
            throw new NoSuchElementException("Wallet not found: " + walletId);
        }

        return transactionRepository.getBalanceByWalletId(walletId);
    }
}
