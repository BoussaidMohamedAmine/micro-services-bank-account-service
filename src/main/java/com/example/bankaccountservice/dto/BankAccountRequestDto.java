package com.example.bankaccountservice.dto;

import com.example.bankaccountservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class BankAccountRequestDto {
    private Double balance;
    private String currency;
    private AccountType type;
}
