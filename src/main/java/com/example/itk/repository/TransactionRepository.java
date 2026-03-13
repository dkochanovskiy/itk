package com.example.itk.repository;

import com.example.itk.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT COALESCE(SUM(CASE WHEN t.operationType = 'DEPOSIT' THEN t.amount ELSE -t.amount END), 0) " +
            "FROM Transaction t WHERE t.walletId = :walletId")
    Integer getBalanceByWalletId(@Param("walletId") UUID walletId);
}