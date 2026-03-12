package com.example.itk.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.itk.entity.Wallet;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Query("SELECT COALESCE(SUM(CASE WHEN w.operationType = 'DEPOSIT' THEN w.amount ELSE -w.amount END), 0) " +
            "FROM Wallet w WHERE w.walletId = :walletId")
    Integer getBalanceByWalletId(@Param("walletId") UUID walletId);
}
