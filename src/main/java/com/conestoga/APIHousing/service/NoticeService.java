package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.interfaces.NoticeRepository;
import com.conestoga.APIHousing.model.Notice;
import com.conestoga.APIHousing.utils.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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


    public Notice createNotice(Notice notice) throws IOException {
         if(notice.getImgUrl() != null && !notice.getImgUrl().isEmpty()){
                    notice.setImgUrl((FileUpload.convertBase64ToFile(notice.getImgUrl())));
        }

        return noticeRepository.save(notice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    // Add other methods for updating and deleting notices (omitted for brevity)
}
