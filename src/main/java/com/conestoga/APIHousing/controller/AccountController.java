package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.AccountDTO;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) {
        Account createdAccount = accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.getAccountById(id);
        if (accountDTO != null) {
            return ResponseEntity.ok(accountDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        Account updatedAccount = accountService.updateAccount(String.valueOf(id), accountDTO);
        if (updatedAccount != null) {
            return ResponseEntity.ok(updatedAccount);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        boolean deleted = accountService.deleteAccount(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        AccountDTO accountDTO = new AccountDTO("banana", "passwordzzzzzz", "yellow@fruit.com", "bananaSplit", "chocolate", "numberzzz", "address2", LocalDate.now().minusMonths(3));
        accountService.createAccount(accountDTO);
        return ResponseEntity.ok("Test worked!");
    }

    @GetMapping("/test2")
    public ResponseEntity<String> test2() {
        return ResponseEntity.ok("Test worked!");
    }
}
