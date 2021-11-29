package com.kea.planit.controllers;

import com.kea.planit.repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class TaskController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/view-tasks")
    public String viewTasks(Model taskModel){
        TaskRepository taskRepository = new TaskRepository();
        taskModel.addAttribute("taskList", taskRepository.getTaskList());
        return "view-tasks";
    }



}
