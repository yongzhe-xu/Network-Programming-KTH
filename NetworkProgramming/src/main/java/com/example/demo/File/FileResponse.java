package com.example.demo.File;

import lombok.Data;

@Data
public class FileResponse {
    private final String message;

    public FileResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}