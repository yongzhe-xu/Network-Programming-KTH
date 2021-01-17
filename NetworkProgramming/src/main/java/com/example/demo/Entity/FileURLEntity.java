package com.example.demo.Entity;

import javax.persistence.*;

@Entity
@Table(name = "file_url")
public class FileURLEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String downloadUrl;
    private String fileName;
    private String groupNum;
    private String sentByWhom;
    private String sentDate;

    public FileURLEntity() {
    }

    public FileURLEntity(String downloadUrl, String fileName, String groupNum, String sentByWhom, String sentDate) {
        this.downloadUrl = downloadUrl;
        this.fileName = fileName;
        this.groupNum = groupNum;
        this.sentByWhom = sentByWhom;
        this.sentDate = sentDate;
    }

    public String getUrl() {
        return downloadUrl;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getSentByWhom() {
        return sentByWhom;
    }

    public void setSentByWhom(String sendByWhom) {
        this.sentByWhom = sendByWhom;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGroup_id() {
        return groupNum;
    }

    public void setGroup_id(String group_num) {
        this.groupNum = group_num;
    }
}
