package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.AccountDTO;
import com.conestoga.APIHousing.dtos.LoginRequest;
import com.conestoga.APIHousing.dtos.LoginResponse;
import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.utils.FileUpload;
import com.conestoga.APIHousing.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    public AccountDTO createAccount(AccountDTO accountDTO) throws IOException {
        Account account = convertToAccount(accountDTO);
        if(account.getImg() != null && !account.getImg().isEmpty()){
                    account.setImg(FileUpload.convertBase64ToFile(account.getImg()));
        }

        return convertToAccountDTO(accountRepository.save(account));
    }

    public LoginResponse loginAccount(LoginRequest loginRequest) {
      

        // Create a new authentication token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // Set the authenticated authentication object in the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate a JWT token
        String token = jwtUtil.generateToken(loginRequest.getEmail());

        //now get account details from email
       // Now get the existing account from the database using email
          Optional<Account> existingAccountOptional = accountRepository.findByEmail(loginRequest.getEmail());
        if (existingAccountOptional.isPresent()) {
            Account existingAccount = existingAccountOptional.get();
            //check if loginRequest.getFcm() is empty or null
            if(loginRequest.getFcm() != null && !loginRequest.getFcm().isEmpty())
            {
                existingAccount.setFcm(loginRequest.getFcm());
            }
            accountRepository.save(existingAccount);
        }

        return new LoginResponse(token,convertToAccountDTO(existingAccountOptional.get()));
    }

   


    public AccountDTO getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.map(this::convertToAccountDTO).orElse(null);
    }

    public AccountDTO getAccountByEmail(String email) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
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
            Account account = convertToAccount(accountDTO);

         
            // account.setEmail(accountDTO.getEmail());
            // account.setPassword(accountDTO.getPassword());
            // account.setEmail(accountDTO.getEmail());
            // account.setFirstName(accountDTO.getFirstName());
            // account.setLastName(accountDTO.getLastName());
            // account.setPhoneNumber(accountDTO.getPhoneNumber());
            // account.setAddress(accountDTO.getAddress());
            // account.setDateOfBirth(accountDTO.getDateOfBirth());
            // account.setFcm(accountDTO.getFcm());
            // account.setCollegeName(accountDTO.getCollegeName());
            // account.setStudentId(accountDTO.getStudentId());
            // account.setPostalCode(accountDTO.getPostalCode());
            // account.setRole(accountDTO.getrole());

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


    @Transactional
    public boolean updatePassword(String password, String email) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setPassword(password);
            accountRepository.save(account);
            return true;
        } else {
    
            return false;    }
    }
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(this::convertToAccountDTO)
                .collect(Collectors.toList());
    }

    public Account convertToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setAddress(accountDTO.getAddress());
        account.setDateOfBirth(accountDTO.getDateOfBirth());
        account.setFcm(accountDTO.getFcm());
        account.setCollegeName(accountDTO.getCollegeName());
        account.setStudentId(accountDTO.getStudentId());
        account.setPostalCode(accountDTO.getPostalCode());
        account.setRole(accountDTO.getRole());
        account.setImg(accountDTO.getImg());

        return account;
    }

    private AccountDTO convertToAccountDTO(Account account) {
      return new AccountDTO(
        account.getId(),
        account.getEmail(),
        account.getFirstName(),
        account.getLastName(),
        account.getPhoneNumber(),
        account.getAddress(),
        account.getDateOfBirth(),
        account.getFcm(),
        account.getCollegeName(),
        account.getStudentId(),
        account.getPostalCode(),
        account.getRole(),
        account.getImg(),
        null
      );
    }
}
