package com.example.bankaccountservice.service;

import com.example.bankaccountservice.dto.BankAccountRequestDto;
import com.example.bankaccountservice.dto.BankAccountResponseDto;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.exceptions.AccountNotFoundException;
import com.example.bankaccountservice.mappers.BankAccountMapper;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper  bankAccountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository,  BankAccountMapper bankAccountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
    }

    @Override
    public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountRequestDto) {
        BankAccount bankAccount = bankAccountMapper.fromRequestDtoToBankAccount(bankAccountRequestDto);
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        return bankAccountMapper.fromBankAccountToBankAccountResponseDto(savedAccount);
    }

    @Override
    public List<BankAccountResponseDto> getAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return accounts.stream()
                .map(bankAccountMapper::fromBankAccountToBankAccountResponseDto)
                .toList();
    }

    @Override
    public BankAccountResponseDto getAccount(String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(()-> new AccountNotFoundException(id));
        return bankAccountMapper.fromBankAccountToBankAccountResponseDto(account);
    }

    @Override
    public BankAccountResponseDto updateAccount(String id, BankAccountRequestDto bankAccountRequestDto) {
        BankAccount account =  bankAccountRepository.findById(id)
                .orElseThrow(()-> new AccountNotFoundException(id));
        bankAccountMapper.updateEntityFromRequest(bankAccountRequestDto,account);
        BankAccount savedAccount = bankAccountRepository.save(account);
        return bankAccountMapper.fromBankAccountToBankAccountResponseDto(savedAccount);
    }

    @Override
    public void deleteAccount(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(()-> new AccountNotFoundException(id));
        bankAccountRepository.delete(bankAccount);
    }
}
