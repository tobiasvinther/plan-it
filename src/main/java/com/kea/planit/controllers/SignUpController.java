package com.kea.planit.controllers;

import com.kea.planit.models.UserModel;
import com.kea.planit.repositories.UserRepository;
import com.kea.planit.services.SignUPServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//Author: Jonatan Segal
@Controller
public class SignUpController {
    private static SignUPServices sus = new SignUPServices();

    @GetMapping("/sign-up")
    public String registration(Model model){
        model.addAttribute("signUpForm", new UserModel());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String userSignUp(@ModelAttribute("signUpForm") UserModel userModel, RedirectAttributes redirAttrs){
        System.out.println("Printing object: "+ userModel);
        if(!sus.isUserValid(userModel)){
            sus.register(userModel);
            System.out.println(UserRepository.getInstance().getAllUsers().toString());
            return"success";
        }
        redirAttrs.addFlashAttribute("error", "User with that email already exists");
        System.out.println("User already exists");
        return"redirect:/sign-up";
    }
}
