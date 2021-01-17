package com.example.demo.RestfulController;

import com.example.demo.Encryption.EncryptionDES;
import com.example.demo.Service.FileSystemStorageInterface;
import com.example.demo.File.FileResponse;
import com.example.demo.File.FileURLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@RestController
public class FileController {
    @Autowired
    FileSystemStorageInterface fileSystemStorage;


    @PostMapping("/uploadfile")
    public ResponseEntity<FileResponse> uploadFile (@RequestParam("file") MultipartFile file,
                                                    @RequestParam("username") String username,
    @RequestParam("roomId") String roomId) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {

        //System.out.println("hahahah");
        String fileName = fileSystemStorage.saveFile(file);

        fileSystemStorage.recordURL(
                EncryptionDES.getInstance().encryptData("https://localhost:8443/download/"+fileName),
                EncryptionDES.getInstance().encryptData(fileName),
                roomId,
                EncryptionDES.getInstance().encryptData(username));
        return ResponseEntity.status(HttpStatus.OK).body(new FileResponse("File uploaded with success!"));
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        System.out.println(filename);
        Resource resource = fileSystemStorage.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_PDF_VALUE,
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/find")
    public ResponseEntity<ArrayList<FileURLResponse>> findAll(
            @RequestParam(name="roomId") String roomId) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        return ResponseEntity.ok().body(fileSystemStorage.findAllPDF(roomId));
    }

}
