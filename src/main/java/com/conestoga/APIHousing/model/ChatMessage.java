package com.conestoga.APIHousing.model;

import java.util.Date;

public class ChatMessage {
    private String senderId;
    private String senderName;
    private String pictureBase64;
    private String content;
    private String contentType;
    private Date date;

    public ChatMessage() {
    }

    public ChatMessage(String senderId, String senderName, String pictureBase64, String content, String contentType, Date date) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.pictureBase64 = pictureBase64;
        this.content = content;
        this.contentType = contentType;
        this.date = date;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
