package com.kea.planit.controllers;

import com.kea.planit.models.User;
import com.kea.planit.repositories.UserRepository;
import com.kea.planit.services.SignUPServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {
    private static SignUPServices sus = new SignUPServices();

    @GetMapping("/sign-up")
    public String registration(Model model){
        model.addAttribute("signUpForm", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String userSignUp(@ModelAttribute("signUpForm") User user){
        System.out.println("Printing object: "+ user);
        if(sus.isUserValid(user)){
            UserRepository.getInstance().addUser(user);
            System.out.println(UserRepository.getInstance().getAllUsers().toString());
            return"success";
        }
        System.out.println("User already exists");
        return"sign-up";
    }
}
