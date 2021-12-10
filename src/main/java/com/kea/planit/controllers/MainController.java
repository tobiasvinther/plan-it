package com.kea.planit.controllers;

import com.kea.planit.repositories.TaskRepository;
import com.kea.planit.services.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private AuthenticationService as = new AuthenticationService();

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/view-tasks")
    public String viewTasks(Model taskModel, Authentication authentication){
        as.findUserId(authentication);
        TaskRepository taskRepository = new TaskRepository();
        taskModel.addAttribute("taskList", taskRepository.getTaskList());
        return "view-tasks";
    }


}
