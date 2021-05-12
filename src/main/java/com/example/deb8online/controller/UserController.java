package com.example.deb8online.controller;

import com.example.deb8online.entity.User;
import com.example.deb8online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    //@Autowired
    //private UserService userService;

    //********************** Renderar förstasidan index.html ***********************//
    @GetMapping("/")
    public String welcome(){

        return "index";
    }

}
