package com.example.itk.controller;

import com.example.itk.dto.OperationType;
import com.example.itk.dto.WalletRequest;
import com.example.itk.dto.WalletResponse;
import com.example.itk.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WalletController.class)
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WalletService walletService;

    @Autowired
    private ObjectMapper objectMapper;

    private final UUID testWalletId = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");

    @Test
    void processWalletOperation_ShouldReturnCreatedWallet() throws Exception {

        WalletRequest request = new WalletRequest();
        request.setWalletId(testWalletId);
        request.setOperationType(OperationType.DEPOSIT);
        request.setAmount(1000);

        WalletResponse mockResponse = new WalletResponse(testWalletId, 1000, "Deposit successful");

        when(walletService.save(any(WalletRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.walletId").value(testWalletId.toString()))
                .andExpect(jsonPath("$.balance").value(1000))
                .andExpect(jsonPath("$.message").value("Deposit successful"));
    }

    @Test
    void getBalance_ShouldReturnWalletBalance() throws Exception {

        Integer expectedBalance = 1500;
        when(walletService.getBalanceByWalletId(testWalletId)).thenReturn(expectedBalance);

        mockMvc.perform(get("/api/v1/wallet/{walletId}/balance", testWalletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.walletId").value(testWalletId.toString()))
                .andExpect(jsonPath("$.balance").value(expectedBalance));
    }
}