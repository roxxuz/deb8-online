package com.example.deb8online.controller;

import com.example.deb8online.entity.Message;
import com.example.deb8online.entity.User;
import com.example.deb8online.service.MessageService;
import com.example.deb8online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    //******************** Huvudsidan för inloggad användare ***************************
    @GetMapping("/message_board/{id}/{alert}")
    public String viewMessageBoard(@PathVariable("id") Long id, @PathVariable("alert") String alert, Model model){

        User user = userService.getUserById(id);

        Message message = new Message();

        message.setMessageUserName(user.getUserName());
        message.setMessageUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("message", message);

        model.addAttribute("messages", messageService.getAllMessages());

        model.addAttribute("alert_msg", alert);

        return "msg_board";
    }

    //**************** En redirect för när URLen saknar en andra variabel *******************
    @GetMapping("/message_board/{id}")
    public String messageBoardRedirect(@PathVariable long id){
        //Eftersom URLen behöver en andra variabel (string) för att fungera
        //så läggs den till här som "none"
        //När den är "none" så visas inget popup meddelande i msg_board.html
        String alert = "/none";
        return "redirect:/message_board/" + id + alert;
    }

    @PostMapping("/save_message")
    public String saveMessage(Message message){

        messageService.saveMessage(message);

        return "redirect:/message_board/" + message.getMessageUserId();
    }

    @PostMapping("/delete_message")
    public String deleteMessage(User user, Message message, Model model){

        if(messageService.deleteMessage(message, user)){

            return "redirect:/message_board/" + user.getId();

        }
        else{
            //Denna string kommer att visas i ett popup-fönster i msg_board.html
            //med hjälp av javascript.
            String alert = "You can only delete your own messages!";
            return "redirect:/message_board/" + user.getId() + "/" + alert;
        }
    }

}
