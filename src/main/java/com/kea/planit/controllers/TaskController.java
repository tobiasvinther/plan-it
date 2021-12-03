package com.kea.planit.controllers;

import com.kea.planit.repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


public class TaskController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/view-tasks/{id}")
    public String viewTasks(@PathVariable("id") int id, Model taskModel){
        TaskRepository taskRepository = new TaskRepository();
        taskModel.addAttribute("taskList", taskRepository.getTaskList(id));
        return "view-tasks";
    }



}
