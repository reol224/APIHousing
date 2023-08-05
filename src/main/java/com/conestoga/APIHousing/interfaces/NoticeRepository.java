package com.conestoga.APIHousing.interfaces;

import com.conestoga.APIHousing.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
  // Add custom query methods (if needed) for specific operations (omitted for brevity)
}
