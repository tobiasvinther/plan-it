package com.kea.planit.controllers;

import com.kea.planit.repositories.TaskRepository;
import com.kea.planit.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        //add the total hours and completion percentage to the model by using the service
        TaskService taskService = new TaskService();
        taskModel.addAttribute("totalHours", taskService.calculateHours(taskRepository.getTaskList()));
        taskModel.addAttribute("completionPercentage", taskService.calculateCompletionPercentage(taskRepository.getTaskList()));
        return "view-tasks";
    }


}
