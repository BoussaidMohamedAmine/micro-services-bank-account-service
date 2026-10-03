package com.example.bankaccountservice.dto;

import com.example.bankaccountservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class BankAccountResponseDto {
    private String id;
    private Date createAt;
    private Double balance;
    private String currency;
    private AccountType type;
}
