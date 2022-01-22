package com.example.guru_cares.modelclass;

public class NoticeData_Model {

    String noticeTitle;
    String noticeData;
    public NoticeData_Model(){

    }

    public NoticeData_Model(String noticeTitle, String noticeData) {
        this.noticeTitle = noticeTitle;
        this.noticeData = noticeData;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeData() {
        return noticeData;
    }

    public void setNoticeData(String noticeData) {
        this.noticeData = noticeData;
    }
}

