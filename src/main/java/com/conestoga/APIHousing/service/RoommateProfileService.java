package com.conestoga.APIHousing.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.interfaces.RoommateProfileRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.model.RoommateProfile;



@Service
@Transactional
 public class RoommateProfileService {
    
    private final RoommateProfileRepository roommateProfileRepository;
        private final AccountRepository accountRepository;


     @Autowired
    public RoommateProfileService(RoommateProfileRepository roommateProfileRepository, AccountRepository accountRepository) {
        this.roommateProfileRepository = roommateProfileRepository;
        this.accountRepository = accountRepository;
    }
      
    public List<RoommateProfile> findAll( ) {
        return roommateProfileRepository.findAll();
    }


     public RoommateProfile createRoommateProfile(Long userId, RoommateProfile roommateProfile) {
        Optional<Account> optionalAccount = accountRepository.findById(userId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            roommateProfile.setUser(account);
            return roommateProfileRepository.save(roommateProfile);
        } else {
            return null;
        }
    
}
  
    public RoommateProfile getRoommateProfileById(Long id) {
        return roommateProfileRepository.findById(id).orElse(null);
    }

  
    public RoommateProfile updateRoommateProfile(Long id, RoommateProfile roommateProfile) {
        RoommateProfile existingProfile = getRoommateProfileById(id);
        if (existingProfile != null) {
            // Update the fields of existingProfile with the values from roommateProfile
            existingProfile.setDescription(roommateProfile.getDescription());
            existingProfile.setSmoking(roommateProfile.getSmoking());
            existingProfile.setDrinking(roommateProfile.getDrinking());
            existingProfile.setPets(roommateProfile.getPets());
            existingProfile.setHobbies(roommateProfile.getHobbies());
            existingProfile.setContactMethod(roommateProfile.getContactMethod());

            return roommateProfileRepository.save(existingProfile);
        }
        return null;
    }


    public void deleteRoommateProfile(Long id) {
        roommateProfileRepository.deleteById(id);
    }

     public Optional<RoommateProfile> getRoommateProfileByUserId(Long userId) {
        return roommateProfileRepository.findByUserId(userId);
    }
}
