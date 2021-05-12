package com.example.deb8online.controller;

import com.example.deb8online.entity.User;
import com.example.deb8online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //********************** Renderar förstasidan index.html ***********************//
    @GetMapping("/")
    public String viewIndex(){

        return "index";
    }

    //************** Renderar signup.html och skickar med nyskapat user objekt ***************//
    @GetMapping("/register")
    public String registerNewUser(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    //************** Sparar ny användare och renderar succeded view ***************//
    @PostMapping("/process_registration")
    public String processRegistration(User user, Model model) {
        userService.saveUser(user);

        model.addAttribute("welcomeMessage", "Welcome " + user.getFirstName());

        return "registration_succeded";
    }

}
