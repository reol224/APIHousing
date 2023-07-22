package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.LeaseDTO;
import com.conestoga.APIHousing.dtos.UnitDTO;
import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.interfaces.LeaseRepository;
import com.conestoga.APIHousing.interfaces.UnitRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.model.Lease;
import com.conestoga.APIHousing.model.Unit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final UnitRepository unitRepository;
    private final AccountRepository accountRepository;

    public LeaseService(LeaseRepository leaseRepository, UnitRepository unitRepository, AccountRepository accountRepository) {
        this.leaseRepository = leaseRepository;
        this.unitRepository = unitRepository;
        this.accountRepository = accountRepository;
    }

    public LeaseDTO createLease(LeaseDTO leaseDTO) {
        Lease lease = convertToLease(leaseDTO);
        Unit unit = unitRepository.findById(leaseDTO.getunit().getUnitId()).orElse(null);
        Account user = accountRepository.findById(leaseDTO.getUserId()).orElse(null);

        if (unit != null && user != null) {
            lease.setUnit(unit);
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
            Unit unit = unitRepository.findById(leaseDTO.getunit().getUnitId()).orElse(null);
            Account user = accountRepository.findById(leaseDTO.getUserId()).orElse(null);

            if (unit != null && user != null) {
                lease.setUnit(unit);
                lease.setUser(user);
                lease.setLeaseStartDate(leaseDTO.getLeaseStartDate());
                lease.setLeaseEndDate(leaseDTO.getLeaseEndDate());
                lease.setLeaseLength(leaseDTO.getLeaseLength());
                lease.setLeaseStatus(leaseDTO.getLeaseStatus());
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
        lease.setLeaseLength(leaseDTO.getLeaseLength());
        lease.setLeaseStatus(leaseDTO.getLeaseStatus());
        return lease;
    }

    private LeaseDTO convertToLeaseDTO(Lease lease) {
        LeaseDTO leaseDTO = new LeaseDTO();
        leaseDTO.setLeaseId(lease.getLeaseId());
        leaseDTO.setunit(new UnitDTO(lease.getUnit()));
        leaseDTO.setUserId(lease.getUser().getUserId());
        leaseDTO.setLeaseStartDate(lease.getLeaseStartDate());
        leaseDTO.setLeaseEndDate(lease.getLeaseEndDate());
        leaseDTO.setLeaseLength(lease.getLeaseLength());
        leaseDTO.setLeaseStatus(lease.getLeaseStatus());
        return leaseDTO;
    }
}
