package com.example.itk.dto;

import java.util.UUID;

public class BalanceResponse {

    private UUID walletId;
    private Integer balance;

    public BalanceResponse(UUID walletId, Integer balance) {

        this.walletId = walletId;
        this.balance = balance;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public Integer getBalance() {
        return balance;
    }
}
