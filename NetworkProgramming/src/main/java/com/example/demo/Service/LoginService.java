package com.example.demo.Service;

import com.example.demo.DAO.UserInfoDAO;
import com.example.demo.Encryption.EncryptionDES;
import com.example.demo.Entity.UserInfoEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class LoginService {
    @Resource
    UserInfoDAO userInfoDAO;


    public boolean validateUser(String name, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        List<UserInfoEntity> userList = userInfoDAO.findByUsername(EncryptionDES.getInstance().encryptData(name));
        if(userList.size() == 0)
        {
            return false;
        }
        System.out.println("password"+EncryptionDES.getInstance().decryptData(userList.get(0).getPassword()));
        return EncryptionDES.getInstance().decryptData(userList.get(0).getPassword()).equals(password);
    }

    public void saveUser(UserInfoEntity user)
    {
        userInfoDAO.save(user);
    }
}
