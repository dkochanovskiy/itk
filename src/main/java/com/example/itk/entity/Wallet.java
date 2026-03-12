package com.example.itk.entity;

import com.example.itk.dto.OperationType;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @Column(name = "walletId")
    private UUID walletId;

    @Enumerated(EnumType.STRING)
    @Column(name = "operationType", nullable = false)
    private OperationType operationType;

    @Column(name = "amount", nullable = false)
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