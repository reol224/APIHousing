package com.conestoga.APIHousing.interfaces;

import com.conestoga.APIHousing.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    ///findByUserId
        List<Notification> findByUserIdOrderByIdDesc(Long userId);

}