package com.example.itk.dto;

import java.util.UUID;

public class WalletResponse {
    private UUID walletId;
    private OperationType operationType;
    private Integer amount;

    public WalletResponse(UUID walletId, OperationType operationType, Integer amount) {
        this.walletId = walletId;
        this.operationType = operationType;
        this.amount = amount;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}