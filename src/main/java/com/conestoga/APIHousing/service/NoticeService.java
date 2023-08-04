package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.interfaces.NoticeRepository;
import com.conestoga.APIHousing.model.Notice;
import com.conestoga.APIHousing.utils.FileUpload;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;
    Logger logger = Logger.getLogger(NoticeService.class.getName());

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<Notice> getAllNotices() {
        logger.info("All notices returned");
        return noticeRepository.findAll();
    }


    public Notice createNotice(Notice notice) throws IOException {
         if(notice.getImgUrl() != null && !notice.getImgUrl().isEmpty()){
                    notice.setImgUrl((FileUpload.convertBase64ToFile(notice.getImgUrl())));
                    logger.info("Notice created: " + notice);
        }

        return noticeRepository.save(notice);
    }

    public void deleteNotice(Long id) {
        logger.info("Notice deleted for id: " + id);
        noticeRepository.deleteById(id);
    }

    // Add other methods for updating and deleting notices (omitted for brevity)
}
