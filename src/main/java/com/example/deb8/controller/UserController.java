package com.example.deb8.controller;

import com.example.deb8.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("")
    public String viewFrontPage() {
        return "index";
    }

    @GetMapping("/register")
    public String viewSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
}
