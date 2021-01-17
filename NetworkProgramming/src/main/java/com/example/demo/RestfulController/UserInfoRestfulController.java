package com.example.demo.RestfulController;

import com.example.demo.Entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserInfoRestfulController {
    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo(HttpSession session)
    {
        return new UserInfo(session.getAttribute("username").toString(),
                session.getAttribute("roomId").toString());
    }
}
