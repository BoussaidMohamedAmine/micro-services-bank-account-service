package com.example.bankaccountservice.mappers;

import com.example.bankaccountservice.dto.BankAccountRequestDto;
import com.example.bankaccountservice.dto.BankAccountResponseDto;
import com.example.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BankAccountMapper {

   public BankAccount fromRequestDtoToBankAccount(BankAccountRequestDto bankAccountDto) {
       BankAccount bankAccount = new BankAccount();
       BeanUtils.copyProperties(bankAccountDto, bankAccount);
       bankAccount.setCreateAt(new Date());
       return bankAccount;
   }

   public BankAccountResponseDto fromBankAccountToBankAccountResponseDto(BankAccount bankAccount) {
       BankAccountResponseDto bankAccountResponseDto = new BankAccountResponseDto();
       BeanUtils.copyProperties(bankAccount, bankAccountResponseDto);
       return bankAccountResponseDto;
   }

    public void updateEntityFromRequest(BankAccountRequestDto request, BankAccount account) {
        if (request.getBalance() != null)  account.setBalance(request.getBalance());
        if (request.getCurrency() != null) account.setCurrency(request.getCurrency());
        if (request.getType() != null)     account.setType(request.getType());
    }

}
