package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.AccountDTO;
import com.conestoga.APIHousing.dtos.LoginRequest;
import com.conestoga.APIHousing.dtos.LoginResponse;
import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.utils.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService  {

    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, AuthenticationManager authenticationManager,
            JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public Account createAccount(AccountDTO accountDTO) {
        Account account = convertToAccount(accountDTO);
        return accountRepository.save(account);
    }

    public LoginResponse loginAccount(LoginRequest loginRequest) {
        // Print request body
        System.out.println(loginRequest);

        // Create a new authentication token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // Set the authenticated authentication object in the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate a JWT token
        String token = jwtUtil.generateToken(loginRequest.getUsername());

        // Retrieve the user details from the authentication object
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new LoginResponse(token, userDetails);
    }

    public AccountDTO getAccountByUsername(String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        return accountOptional.map(this::convertToAccountDTO).orElse(null);
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
