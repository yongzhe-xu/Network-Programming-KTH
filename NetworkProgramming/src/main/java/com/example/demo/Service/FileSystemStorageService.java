package com.example.demo.Service;

import com.example.demo.DAO.FileUrlDAO;
import com.example.demo.Encryption.EncryptionDES;
import com.example.demo.Entity.FileURLEntity;
import com.example.demo.File.FileURLResponse;
import com.example.demo.File.FileUploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class FileSystemStorageService implements FileSystemStorageInterface{
    private final Path dirPath;

    @javax.annotation.Resource
    private FileUrlDAO fileUrlDAO;

    @Autowired
    public FileSystemStorageService(FileUploadProperties fileUploadProperties) {
        this.dirPath = Paths.get(fileUploadProperties.getLocation());//small change here
        //System.out.println(dirPath);
        //this.dirPath = Path.of("/Users/mac/Desktop/images_restful");
    }

    @Override
    public String saveFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();

            //copy file into directory
            Files.copy(file.getInputStream(), this.dirPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            //return file name if succeed
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Fail to upload file");
        }
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            Resource resource = new UrlResource(this.dirPath.resolve(fileName)
                    .normalize().toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException("No such file");
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Could not download file");
        }
    }

    @Override
    public ArrayList<FileURLResponse> findAllPDF(String group) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        List<FileURLEntity> files = fileUrlDAO.findByGroupNum(group);
        ArrayList<FileURLResponse> list = new ArrayList<>();
        for(FileURLEntity file : files)
        {
            list.add(new FileURLResponse(EncryptionDES.getInstance().decryptData(file.getFileName()),
                    EncryptionDES.getInstance().decryptData(file.getUrl()),
                    EncryptionDES.getInstance().decryptData(file.getSentByWhom()),
                    file.getSentDate()));
        }

        return list;
    }

    @Override
    public void recordURL(String url, String fileName, String group, String sentByWhom)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        fileUrlDAO.save(new FileURLEntity(url, fileName, group, sentByWhom, timestamp.toString()));
    }
}