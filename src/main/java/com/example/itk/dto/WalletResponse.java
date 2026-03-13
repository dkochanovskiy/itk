package com.example.itk.dto;

import java.util.UUID;

public class WalletResponse {

    private UUID walletId;
    private Integer balance;
    private String message;

    public WalletResponse(UUID walletId, Integer balance, String message) {

        this.walletId = walletId;
        this.balance = balance;
        this.message = message;
    }

    public UUID getWalletId() {

        return walletId;
    }

    public void setWalletId(UUID walletId) {

        this.walletId = walletId;
    }

    public Integer getBalance() {

        return balance;
    }

    public void setBalance(Integer balance) {

        this.balance = balance;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }
}