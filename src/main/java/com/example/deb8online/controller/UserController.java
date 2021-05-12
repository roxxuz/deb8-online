package com.example.deb8online.controller;

import com.example.deb8online.entity.User;
import com.example.deb8online.repository.UserRepository;
import com.example.deb8online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //********************** Renderar förstasidan index.html ***********************//
    @GetMapping("/")
    public String viewIndex(Model model){
        model.addAttribute("user", new User());

        return "index";
    }

    //************** Renderar signup.html och skickar med nyskapat user objekt ***************//
    @GetMapping("/register")
    public String registerNewUser(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    //********* Sparar ny användare och renderar succeded view med ett welcome message*********//
    @PostMapping("/process_registration")
    public String processRegistration(User user, Model model) {
        userService.saveUser(user);

        model.addAttribute("welcomeMessage",
                "Welcome " + user.getFirstName() +
                ". You are member nr " + userService.getUserCount());

        return "registration_succeded";
    }

    //************* Kontrollerar användarnamn och lösenord mot databasen *************//
    @PostMapping("/process_login")
    public String process_login(User user, Model model){

        if(userService.authUser(user)){
            return "redirect:/message_board/" + userService.getUserByUserName(user.getUserName()).getId();
        }
        else{
            model.addAttribute("msg", "Incorrect username or password!");
            return "index";
        }
    }

    @GetMapping("/message_board/{id}")
    public String viewMessageBoard(@PathVariable Long id, Model model){

        User user = userService.getUserById(id);

        model.addAttribute(user);

        return "msg_board";
    }

}
