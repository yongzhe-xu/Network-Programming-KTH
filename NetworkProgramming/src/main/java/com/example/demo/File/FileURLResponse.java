package com.example.demo.File;

public class FileURLResponse {
    private final String fileName;
    private final String downloadURL;
    private final String sentByWhom;
    private final String time;

    public FileURLResponse(String fileName, String downloadURL, String sentByWhom, String time) {
        this.fileName = fileName;
        this.downloadURL = downloadURL;
        this.sentByWhom = sentByWhom;
        this.time = time;
    }

    public String getSentByWhom() {
        return sentByWhom;
    }

    public String getTime() {
        return time;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDownloadURL() {
        return downloadURL;
    }
}
