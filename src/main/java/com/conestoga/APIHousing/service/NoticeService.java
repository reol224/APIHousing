package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.interfaces.NoticeRepository;
import com.conestoga.APIHousing.model.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<Notice> getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();
        return notices;
    }


    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    // Add other methods for updating and deleting notices (omitted for brevity)
}
