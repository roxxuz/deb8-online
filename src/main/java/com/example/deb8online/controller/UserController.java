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
    @GetMapping("/{msg}")
    public String viewIndex(@PathVariable("msg") String msg, Model model){
        model.addAttribute("user", new User());
        model.addAttribute("msg",msg);
        return "index";
    }

    //**********Redirect om sidan besöks utan något message***********************
    @GetMapping("/")
    public String viewIndexRedirect(){
        return "redirect:/none";
    }

    //************** Renderar signup.html och skickar med nyskapat user objekt ***************//
    @GetMapping("/register/{msg}")
    public String registerNewUser(@PathVariable("msg") String msg, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("msg",msg);
        return "signup";
    }

    @GetMapping("/register")
    public String registerNewUserRedirect(){
        return "redirect:/register/none";
    }

    //********* Sparar ny användare och renderar succeded view med ett welcome message*********//
    @PostMapping("/process_registration")
    public String processRegistration(User user, Model model) {

        //Kontrollerar så att inte användaren redan finns i databasen
        //Om användaren inte finns så kommer getUserByUserName att returnera null och if-satsen körs
        if(userService.getUserByUserName(user.getUserName()) == null) {

            userService.saveUser(user);

            model.addAttribute("welcomeMessage",
                    "Welcome " + user.getFirstName() +
                            ". You are member nr " + userService.getUserCount());

            return "registration_succeded";
        }
        else{
            String msg = "This username is not available";
            return "redirect:/register/" + msg;
        }
    }

    //************* Kontrollerar användarnamn och lösenord mot databasen *************//
    @PostMapping("/process_login")
    public String processLogin(User user, Model model){

        if(userService.authUser(user)){
            return "redirect:/message_board/" + userService.getUserByUserName(user.getUserName()).getId();
        }
        else{
            model.addAttribute("msg", "Incorrect username or password!");
            return "index";
        }
    }

    @GetMapping("/user_settings/{id}/{msg}")
    public String editUser(@PathVariable("id") long id, @PathVariable("msg") String msg, Model model){

        User user = userService.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("msg", msg);

        return "edit_user";
    }

    @GetMapping("/user_settings/{id}")
    public String editUserRedirect(@PathVariable long id){
        //Om user_settings besökts med endast användar-id så sätts msg till "none"
        //och inget meddelande visas då i (Thymeleaf if-satsen) i edit_user.html
        String msg = "none";
        return "redirect:/user_settings/" + id + "/" + msg;
    }

    @GetMapping("/sign_out/{id}")
    public String signOut(@PathVariable("id") long id){


        return "redirect:/";
    }

    @PostMapping("/update_user")
    public String updateUser(User user){

        userService.updateUser(user);

        String msg = "Settings updated!";

        return "redirect:/user_settings/" + user.getId() + "/" + msg;
    }

    @GetMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable("id") long id){

        User user = userService.getUserById(id);
        userService.deleteUser(user);

        String msg = "User deleted. Bye bye!";
        return "redirect:/" + msg;
    }
}
