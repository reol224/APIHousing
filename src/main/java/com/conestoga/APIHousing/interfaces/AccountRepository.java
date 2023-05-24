package com.conestoga.APIHousing.interfaces;

import com.conestoga.APIHousing.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    List<Account> findByLastName(String lastName);
    List<Account> findByAddress(String address);
    List<Account> findByDateOfBirth(LocalDate dateOfBirth);
}
