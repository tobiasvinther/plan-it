package com.kea.planit.controllers;

import com.kea.planit.models.Task;
import com.kea.planit.repositories.TaskRepository;
import com.kea.planit.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

//Author: Tobias Vinther

@Controller
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/view-tasks")
    public String viewTasks(Model taskModel){

        //taskRepository.populateTaskList();
        taskModel.addAttribute("taskList", taskRepository.getTaskInThisSubproject(1)); //hardcoded for testing

        //add the total hours and completion percentage to the model by using the service
        TaskService taskService = new TaskService();
        taskModel.addAttribute("totalHours", taskService.calculateHours(taskRepository.getTaskInThisSubproject(1))); //hardcoded for testing
        taskModel.addAttribute("completionPercentage", taskService.calculateCompletionPercentage(taskRepository.getTaskInThisSubproject(1))); //hardcoded for testing
        return "view-tasks";
    }

    @PostMapping(value = "/view-tasks")
    public String addTask(WebRequest userInput) {

        //create a new task based on user input
        Task newTask = new Task(
                userInput.getParameter("newTaskName"),
                userInput.getParameter("newTaskDescription"),
                Integer.parseInt(userInput.getParameter("newTaskHours")),
                "Pending",
                Date.valueOf("2022-12-12"),
                1 //hardcoded for testing purposes
        );

        taskRepository.addToTaskList(newTask);
        System.out.println("Task added: " + userInput.getParameter("newTaskName"));
        //return "redirect:/view-all-wishes?wishlist_id=" + userInput.getParameter("wishlist_id");
        return "redirect:/view-tasks";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam String id, HttpSession session){

        //parsing the id as an int since we are receiving it as a String
        int parsedId = Integer.parseInt(id);

        //cast getAttribute as a basket, because we receive it as a generic object, even though it is a Basket
        //Basket myBasket = (Basket)session.getAttribute("myBasket");

        taskRepository.deleteTask(parsedId);

        return "redirect:/view-tasks";
    }

}
