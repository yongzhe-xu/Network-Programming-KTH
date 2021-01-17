package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RoomSelectionController {
    @PostMapping("/RoomSelection")
    public String selectRoom(
            @RequestParam(name="roomId") String roomId,
            HttpSession httpSession)
    {
        httpSession.setAttribute("roomId", roomId);
        return "ChatRoom";
    }

    @GetMapping("/ChangeRoom")
    public String changeRom()
    {
        return "SelectRoom";
    }

    @GetMapping("/Logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("username");
        session.removeAttribute("roomId");
        return "redirect:/index.html";
    }

    @GetMapping("/FindPDF")
    public String findPDF()
    {
        return "FindPDF";
    }

    @GetMapping("/GoBackToRoom")
    public String goBackToRoom()
    {
        return "ChatRoom";
    }

    @GetMapping("/Upload")
    public String upload()
    {
        return "Upload";
    }

}
