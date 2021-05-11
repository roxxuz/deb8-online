package com.example.deb8.controller;

import com.example.deb8.entity.User;
import com.example.deb8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewFrontPage() {
        return "index";
    }

    @GetMapping("/register")
    public String viewSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_registration")
    public String processRegistration(User user) {
        userRepository.save(user);

        return "registration_success";
    }

}
