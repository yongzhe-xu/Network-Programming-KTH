package com.example.demo.Controller;

import com.example.demo.Encryption.EncryptionDES;
import com.example.demo.Entity.UserInfoEntity;
import com.example.demo.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestParam(name="username") String username,
                        @RequestParam(name="password") String password,
                        HttpSession session) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        if(loginService.validateUser(username, password))
        {
            session.setAttribute("username", username);
            return "SelectRoom";
        }
        return "Register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(name="username_r") String username,
                           @RequestParam(name="password_r") String password,
                           HttpSession session) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        UserInfoEntity userInfoEntity = new UserInfoEntity(EncryptionDES.getInstance().encryptData(username), EncryptionDES.getInstance().encryptData(password));
        loginService.saveUser(userInfoEntity);
        session.setAttribute("username", username);

        return "RegsiterSucceed";
    }
}
