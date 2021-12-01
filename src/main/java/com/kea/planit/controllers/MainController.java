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

        //get list of tasks for selected subproject
        taskModel.addAttribute("taskList", taskRepository.getTaskInThisSubproject(5)); //hardcoded for testing

        //add the total hours and completion percentage to the model by using the service
        TaskService taskService = new TaskService();
        taskModel.addAttribute("totalHours", taskService.calculateHours(taskRepository.getTaskInThisSubproject(1))); //hardcoded for testing
        taskModel.addAttribute("completionPercentage", taskService.calculateCompletionPercentage(taskRepository.getTaskInThisSubproject(1))); //hardcoded for testing
        taskModel.addAttribute("statusCategories", taskService.getStatusCategories());
        return "view-tasks";
    }

    @PostMapping("/add-task")
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
        return "redirect:/view-tasks";
    }

    @PostMapping("/edit-task0")
    public String editTask0(WebRequest userInput) {

        //edit task based on user input
        Task taskToEdit = new Task(
                userInput.getParameter("editTaskName"),
                userInput.getParameter("editTaskDescription"),
                Integer.parseInt(userInput.getParameter("editTaskHours")),
                "Pending",
                Date.valueOf("2022-12-12"),
                1 //hardcoded for testing purposes
        );

        taskRepository.editTask(taskToEdit);
        return "redirect:/view-tasks";
    }

    @PostMapping("/edit-task")
    public String editTask(WebRequest userInput) {

        //parse RequestParam and use it to fetch model of the task to edit
        int parsedId = Integer.parseInt(userInput.getParameter("editTaskId"));
        Task editedTask = taskRepository.fetchTaskById(parsedId);
        System.out.println("Edited task id when fetched: " + editedTask.getId()); //debug

        //edit task model based on user input
        editedTask.setName(userInput.getParameter("editTaskName"));
        editedTask.setDescription(userInput.getParameter("editTaskDescription"));
        editedTask.setHours(Integer.parseInt(userInput.getParameter("editTaskHours")));

        //send model task back to database
        taskRepository.editTask(editedTask); //debug
        System.out.println("Edited task id when fetched: " + editedTask.getId());
        return "redirect:/view-tasks";
    }

    //todo: finish this method
    @GetMapping("/change-status")
    public String changeStatus(@RequestParam String id, @RequestParam String updatedStatus){

        //parsing the id as an int since we are receiving it as a String
        int parsedId = Integer.parseInt(id);
        //fetch the task in question
        Task editedTask = taskRepository.fetchTaskById(parsedId);
        //edit the task's status
        editedTask.setStatus(updatedStatus);
        //update task in database
        taskRepository.editTask(editedTask);
        System.out.println("Changed status to " + updatedStatus);
        return "redirect:/view-tasks";
    }

    @GetMapping("/delete-task")
    public String deleteTask(@RequestParam String id){

        //parsing the id as an int since we are receiving it as a String
        int parsedId = Integer.parseInt(id);

        taskRepository.deleteTask(parsedId);
        System.out.println("Deleted task: " + parsedId);

        return "redirect:/view-tasks";
    }

    /*
    @PostMapping("/delete-task")
    public String deleteTask(WebRequest userInput){
        taskRepository.deleteTask(Integer.parseInt(userInput.getParameter("deleteTaskId")));

        return "redirect:/view-tasks";
    }

     */

}
