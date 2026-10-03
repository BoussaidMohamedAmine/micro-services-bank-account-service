package com.example.bankaccountservice.web;

import com.example.bankaccountservice.dto.BankAccountRequestDto;
import com.example.bankaccountservice.dto.BankAccountResponseDto;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.exceptions.AccountNotFoundException;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import com.example.bankaccountservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class AccountRestController {

    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDto> bankAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDto bankAccount(@PathVariable String id) {
        return accountService.getAccount(id);
    }

    // best Practice
    @PostMapping("/bankAccounts")
    public BankAccountResponseDto save(@RequestBody BankAccountRequestDto requestDto){
        return accountService.addAccount(requestDto);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDto update(@PathVariable String id, @RequestBody BankAccountRequestDto bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
         accountService.deleteAccount(id);
    }
}
