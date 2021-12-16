package com.kea.planit.controllers;

import com.kea.planit.models.Subproject;
import com.kea.planit.models.Task;
import com.kea.planit.repositories.SubprojectRepository;
import com.kea.planit.repositories.TaskRepository;
import com.kea.planit.services.AuthenticationService;
import com.kea.planit.services.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;

//Author: Tobias Vinther

@Controller
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();
    SubprojectRepository subprojectRepository = new SubprojectRepository();

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/view-tasks")
    public String viewTasks(Model taskModel, @RequestParam String subprojectId){

        int parsedSubprojectId = Integer.parseInt(subprojectId);
        //get list of tasks for selected subproject
        taskModel.addAttribute("taskList", taskRepository.getTaskInThisSubproject(parsedSubprojectId));
        Subproject thisSubproject = subprojectRepository.fetchSubprojectById(parsedSubprojectId);

        //new stuff
        taskModel.addAttribute("subprojectList", subprojectRepository.getSubprojectsInThisProject(thisSubproject.getSubprojectOwner()));
        taskModel.addAttribute("thisProjectId", thisSubproject.getSubprojectOwner());
        taskModel.addAttribute("thisSubproject", subprojectRepository.fetchSubprojectById(parsedSubprojectId));

        //add the total hours and completion percentage to the model by using the service
        TaskService taskService = new TaskService();
        taskModel.addAttribute("totalHours", taskService.calculateHours(taskRepository.getTaskInThisSubproject(parsedSubprojectId))); //hardcoded for testing
        taskModel.addAttribute("completionPercentage", taskService.calculateCompletionPercentage(taskRepository.getTaskInThisSubproject(parsedSubprojectId))); //hardcoded for testing
        taskModel.addAttribute("statusCategories", taskService.getStatusCategories());
        return "view-tasks";
    }

    @PostMapping("/add-task")
    public String addTask(WebRequest userInput, RedirectAttributes redirectAttributes) {

        //create a new task based on user input
        Task newTask = new Task(
                userInput.getParameter("newTaskName"),
                userInput.getParameter("newTaskDescription"),
                Integer.parseInt(userInput.getParameter("newTaskHours")),
                "Pending",
                Date.valueOf("2022-12-12"),//hardcoded for testing purposes
                Integer.parseInt(userInput.getParameter("newTasksubprojectId"))
                //4 //harcoded for test
        );

        taskRepository.addToTaskList(newTask);
        System.out.println("Task add request sent to database: " + userInput.getParameter("newTaskName"));

        redirectAttributes.addAttribute("subprojectId", userInput.getParameter("newTasksubprojectId"));

        return "redirect:/view-tasks";
    }

    @PostMapping("/edit-task")
    public String editTask(WebRequest userInput, RedirectAttributes redirectAttributes) {

        //parse RequestParam and use it to fetch model of the task to edit
        int parsedId = Integer.parseInt(userInput.getParameter("editTaskId"));
        Task editedTask = taskRepository.fetchTaskById(parsedId);
        System.out.println("Edited task id when fetched: " + editedTask.getId()); //debug

        //edit task model based on user input
        editedTask.setName(userInput.getParameter("editTaskName"));
        editedTask.setDescription(userInput.getParameter("editTaskDescription"));
        editedTask.setHours(Integer.parseInt(userInput.getParameter("editTaskHours")));

        //send model task back to database
        taskRepository.editTask(editedTask);
        System.out.println("Edited task id when fetched: " + editedTask.getId());

        redirectAttributes.addAttribute("subprojectId", userInput.getParameter("editTaskSubprojectId"));

        return "redirect:/view-tasks";
        //return "redirect:/view-tasks?subprojectId=" + userInput.getParameter("editTasksubprojectId") + "&projectId=" + userInput.getParameter("editTaskProjectId");
        //return "redirect:/view-all-wishes?wishlist_id=" + userInput.getParameter("wishlist_id");
        ///view-tasks?subprojectId=4&projectId=8
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
    public String deleteTask(@RequestParam String id, @RequestParam String subprojectId, RedirectAttributes redirectAttributes){

        //parsing the id as an int since we are receiving it as a String
        int parsedId = Integer.parseInt(id);

        taskRepository.deleteTask(parsedId);
        System.out.println("Deleted task: " + parsedId);

        redirectAttributes.addAttribute("subprojectId", subprojectId);

        return "redirect:/view-tasks";
    }

}
