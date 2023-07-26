package com.conestoga.APIHousing.service;


import com.conestoga.APIHousing.interfaces.NotificationRepository;
import com.conestoga.APIHousing.model.Notification;
import com.conestoga.APIHousing.utils.Constants;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final FirebaseService firebaseService;
    private final AccountService accountService;


    @Autowired
    public NotificationService(NotificationRepository notificationRepository, FirebaseService firebaseService, AccountService accountService) {
        this.notificationRepository = notificationRepository;
        this.firebaseService = firebaseService;
        this.accountService = accountService;
    }

    //find by user id
    public List<Notification> findByUserId(Long userId) {
        return notificationRepository.findByUserIdOrderByIdDesc(userId);
    }
    //create
    public Notification create(Notification notification) {
        Notification n = notificationRepository.save(notification);
        String fcm = null;
        if(notification.getUserId() != null) {
            fcm = accountService.getFcmToken(notification.getUserId());
        } 
        firebaseService.sendPushNotification(notification.getTitle(),"", fcm);
        return n;


    }
    // String getTitleByType(int type){
    //     switch (type) {
    //         case Constants.NOTIFICATION_TYPE_NOTICE:
    //             return "New Notice";
    //         case Constants.NOTIFICATION_TYPE_EVENT:
    //             return "New Booking";
    //         case Constants.NOTIFICATION_TYPE_REVIEW:
    //             return "New Review";
    //         default:
    //             return "New Notification";
    //     }
    // }

}