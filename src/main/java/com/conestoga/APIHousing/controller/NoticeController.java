package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.model.Notice;
import com.conestoga.APIHousing.service.FirebaseService;
import com.conestoga.APIHousing.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;
    private final FirebaseService firebaseService;


    @Autowired
    public NoticeController(NoticeService noticeService, FirebaseService firebaseService) {
        this.noticeService = noticeService;
        this.firebaseService = firebaseService;
    }

    @GetMapping
    public ResponseEntity<List<Notice>> getAllNotices() {
        List<Notice> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

   
    @PostMapping
    public ResponseEntity<Notice> createNotice(@RequestBody Notice Notice) {
        Notice createdNotice = noticeService.createNotice(Notice);
        if (createdNotice != null) {
            firebaseService.sendPushNotification(createdNotice.getTitle(), createdNotice.getBody());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdNotice);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    //delete notice
    @DeleteMapping("/{id}")
    public ResponseEntity<Notice> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok().build();
    }

    // Add other endpoints for updating and deleting notices (omitted for brevity)
}
