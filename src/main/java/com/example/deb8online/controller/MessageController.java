package com.example.deb8online.controller;

import com.example.deb8online.entity.Message;
import com.example.deb8online.entity.User;
import com.example.deb8online.service.MessageService;
import com.example.deb8online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    //******************** Huvudsidan för inloggad användare ***************************
    @GetMapping("/message_board/{id}")
    public String viewMessageBoard(@ModelAttribute("message") Message message, @PathVariable Long id, Model model){

        User user = userService.getUserById(id);

        // Message message = new Message();

        message.setMessageUserName(user.getUserName());

        model.addAttribute("user", user);

        return "msg_board";
    }

    @PostMapping("/save_message")
    public String saveMessage(Message message){

        messageService.saveMessage(message);

        return "redirect:/";
    }

}
