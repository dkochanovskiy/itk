package com.example.itk.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class WalletRequest {

    @NotNull(message = "walletId cannot be null")
    private UUID walletId;

    @NotNull(message = "operationType cannot be null")
    private OperationType operationType;

    @NotNull(message = "amount cannot be null")
    @Positive(message = "amount must be positive")
    private Integer amount;

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