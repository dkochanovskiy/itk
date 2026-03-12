package com.example.itk.controller;

import com.example.itk.dto.BalanceResponse;
import com.example.itk.dto.WalletRequest;
import com.example.itk.dto.WalletResponse;
import com.example.itk.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletResponse> save(
            @RequestBody WalletRequest request) {

        WalletResponse response = walletService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{walletId}/balance")
    public ResponseEntity<BalanceResponse> getWalletBalance(@PathVariable UUID walletId) {
        Integer balance = walletService.getBalanceByWalletId(walletId);
        BalanceResponse response = new BalanceResponse(walletId, balance);
        return ResponseEntity.ok(response);
    }
}
