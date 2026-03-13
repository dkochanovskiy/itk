package com.example.itk.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @Column(name = "wallet_id")
    private UUID walletId;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    public Wallet() {}

    public Wallet(UUID walletId) {
        this.walletId = walletId;
        this.amount = 0;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}