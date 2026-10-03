package com.example.bankaccountservice.service;

import com.example.bankaccountservice.dto.BankAccountRequestDto;
import com.example.bankaccountservice.dto.BankAccountResponseDto;
import com.example.bankaccountservice.entities.BankAccount;

import java.util.List;

public interface AccountService {
    public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountDto);
    public List<BankAccountResponseDto> getAccounts();
    public BankAccountResponseDto getAccount(String id);
    public BankAccountResponseDto updateAccount(String id , BankAccountRequestDto bankAccountRequestDto);
    public void deleteAccount(String id);
}
