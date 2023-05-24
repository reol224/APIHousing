package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.AccountDTO;
import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(AccountDTO accountDTO) {
        Account account = convertToAccount(accountDTO);
        return accountRepository.save(account);
    }

    public AccountDTO getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.map(this::convertToAccountDTO).orElse(null);
    }

    public Account updateAccount(String accountId, AccountDTO accountDTO) {
        long id;
        try {
            id = Long.parseLong(accountId);
        } catch (NumberFormatException e) {
            return null;
        }

        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setUsername(accountDTO.getUsername());
            account.setPassword(accountDTO.getPassword());
            account.setEmail(accountDTO.getEmail());
            account.setFirstName(accountDTO.getFirstName());
            account.setLastName(accountDTO.getLastName());
            account.setPhoneNumber(accountDTO.getPhoneNumber());
            account.setAddress(accountDTO.getAddress());
            account.setDateOfBirth(accountDTO.getDateOfBirth());
            return accountRepository.save(account);
        }
        return null;
    }

    public boolean deleteAccount(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(this::convertToAccountDTO)
                .collect(Collectors.toList());
    }

    private Account convertToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setPassword(accountDTO.getPassword());
        account.setEmail(accountDTO.getEmail());
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setAddress(accountDTO.getAddress());
        account.setDateOfBirth(accountDTO.getDateOfBirth());
        return account;
    }

    private AccountDTO convertToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setFirstName(account.getFirstName());
        accountDTO.setLastName(account.getLastName());
        accountDTO.setPhoneNumber(account.getPhoneNumber());
        accountDTO.setAddress(account.getAddress());
        accountDTO.setDateOfBirth(account.getDateOfBirth());
        return accountDTO;
    }
}
