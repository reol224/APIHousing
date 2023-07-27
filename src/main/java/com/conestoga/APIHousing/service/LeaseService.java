package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.LeaseDTO;
import com.conestoga.APIHousing.dtos.SubresidenceDTO;
import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.interfaces.LeaseRepository;
import com.conestoga.APIHousing.interfaces.SubresidenceRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.model.Lease;
import com.conestoga.APIHousing.model.Subresidence;

import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final SubresidenceRepository subresidenceRepository;
    private final AccountRepository accountRepository;

    public LeaseService(LeaseRepository leaseRepository, SubresidenceRepository subresidenceRepository, AccountRepository accountRepository) {
        this.leaseRepository = leaseRepository;
        this.subresidenceRepository = subresidenceRepository;
        this.accountRepository = accountRepository;
    }

    public LeaseDTO createLease(LeaseDTO leaseDTO) {
        Lease lease = convertToLease(leaseDTO);
        lease.setLeaseStatus(0);
        Subresidence subresidence = subresidenceRepository.findById(leaseDTO.getSubresidenceId()).orElse(null);
        Account user = accountRepository.findById(leaseDTO.getUserId()).orElse(null);

        if (subresidence != null && user != null) {
            lease.setSubresience(subresidence);
            lease.setUser(user);
            Lease createdLease = leaseRepository.save(lease);
            return convertToLeaseDTO(createdLease);
        }
        return null;
    }

    public LeaseDTO getLeaseById(Long leaseId) {
        Optional<Lease> leaseOptional = leaseRepository.findById(leaseId);
        return leaseOptional.map(this::convertToLeaseDTO).orElse(null);
    }

    public LeaseDTO updateLease(Long leaseId, LeaseDTO leaseDTO) {
        Optional<Lease> leaseOptional = leaseRepository.findById(leaseId);
        if (leaseOptional.isPresent()) {
            Lease lease = leaseOptional.get();
            Subresidence Subresidence = subresidenceRepository.findById(leaseDTO.getSubresidenceId()).orElse(null);
            Account user = accountRepository.findById(leaseDTO.getUserId()).orElse(null);

            if (Subresidence != null && user != null) {
                lease.setSubresience(Subresidence);
                lease.setUser(user);
                lease.setLeaseStartDate(leaseDTO.getLeaseStartDate());
                lease.setLeaseEndDate(leaseDTO.getLeaseEndDate());
                lease.setLeaseStatus(leaseDTO.getLeaseStatus());
                lease.setUnitNo(leaseDTO.getUnitNo());
                
                Lease updatedLease = leaseRepository.save(lease);
                return convertToLeaseDTO(updatedLease);
            }
        }
        return null;
    }

    public boolean deleteLease(Long leaseId) {
        if (leaseRepository.existsById(leaseId)) {
            leaseRepository.deleteById(leaseId);
            return true;
        }
        return false;
    }

    public List<LeaseDTO> getAllLeases() {
        List<Lease> leases = leaseRepository.findAll();
        return leases.stream()
                .map(this::convertToLeaseDTO)
                .collect(Collectors.toList());
    }

    //getLeaseByUserId
    public List<LeaseDTO> getLeaseByUserId(Long userId) {
        //FIND ACCOUNT BY USERID
        Optional<Account> accountOptional = accountRepository.findById(userId);
        // if account exists, find leases by account, else return null
        if (!accountOptional.isPresent()) {
            return null;
        }

        List<Lease> leases = leaseRepository.findByUser(accountOptional.get());
        return leases.stream()
                .map(this::convertToLeaseDTO)
                .collect(Collectors.toList());
    }

    private Lease convertToLease(LeaseDTO leaseDTO) {
        Lease lease = new Lease();
        lease.setLeaseStartDate(leaseDTO.getLeaseStartDate());
        lease.setLeaseEndDate(leaseDTO.getLeaseEndDate());
        lease.setLeaseStatus(leaseDTO.getLeaseStatus());
        lease.setUnitNo(leaseDTO.getUnitNo());
        return lease;
    }

    private LeaseDTO convertToLeaseDTO(Lease lease) {
        LeaseDTO leaseDTO = new LeaseDTO();
        leaseDTO.setLeaseId(lease.getLeaseId());
        leaseDTO.setUserId(lease.getUser().getId());
        leaseDTO.setSubresidenceId(lease.getSubresidence().getunit_id());
        leaseDTO.setSubresidence(new SubresidenceDTO(lease.getSubresidence()));
        leaseDTO.setUser(AccountService.convertToAccountDTO(lease.getUser()));
        leaseDTO.setLeaseStartDate(lease.getLeaseStartDate());
        leaseDTO.setLeaseEndDate(lease.getLeaseEndDate());
        leaseDTO.setLeaseStatus(lease.getLeaseStatus());
        leaseDTO.setUnitNo(lease.getUnitNo());
        return leaseDTO;
    }
}
