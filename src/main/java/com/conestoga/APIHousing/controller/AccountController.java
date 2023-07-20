package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.AccountDTO;
import com.conestoga.APIHousing.dtos.LoginRequest;
import com.conestoga.APIHousing.dtos.LoginResponse;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.service.AccountService;
import com.conestoga.APIHousing.utils.ErrorResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginAccount(@RequestBody LoginRequest loginRequest) {
        // Call the loginAccount method in the AccountService
        LoginResponse loginResponse = accountService.loginAccount(loginRequest);

        // Return the LoginResponse object as the response with the appropriate HTTP
        // status
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO) throws IOException {
        // print request body
        System.out.println("Request body:");
        System.out.println(accountDTO);
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
    public ResponseEntity<String> test2() {
        return ResponseEntity.ok("Test worked!");
    }



      @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        //print detailed error message
        System.out.println("Error: " + ex.getMessage());
        String errorMessage = "User with this email already exists.";
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, errorMessage);
        System.out.println("Error: " + ex.getStackTrace().toString());
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

}
