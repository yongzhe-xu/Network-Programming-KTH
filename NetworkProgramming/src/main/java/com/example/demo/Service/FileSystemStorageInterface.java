package com.example.demo.Service;

import com.example.demo.File.FileURLResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface FileSystemStorageInterface {
    String saveFile(MultipartFile file);
    Resource loadFile(String fileName);
    ArrayList<FileURLResponse> findAllPDF(String group) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException;
    void recordURL(String url, String fileName, String group, String sentByWhom);
}
