package com.kea.planit.controllers;

import com.kea.planit.models.Project;
import com.kea.planit.models.Task;
import com.kea.planit.repositories.ProjectRepository;
import com.kea.planit.repositories.TaskRepository;
import com.kea.planit.services.AuthenticationService;
import com.kea.planit.services.ProjectService;
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

@Controller
public class ProjectController {
    //Instance
    ProjectRepository projectRepository = new ProjectRepository();
    private AuthenticationService authService = new AuthenticationService();


    @GetMapping("/view-projects")
    public String viewProject(Model projectModel, Authentication authentication){
        projectModel.addAttribute("projectList", projectRepository.viewProject(authService.findUserId(authentication)));
        return "view-projects";

    }
    @PostMapping("/add-project")
    public String addProject(WebRequest userInput, RedirectAttributes redirectAttributes) {
        //Create a new project
        Project newProject = new Project(
                userInput.getParameter("newProjectName"),
                "Pending",
                Date.valueOf("2022-12-12"),
                1
        );

        projectRepository.addToProjectList(newProject);
        System.out.println("Project added: " + userInput.getParameter("newProjectName"));

        redirectAttributes.addAttribute("subprojectId", userInput.getParameter("newTasksubprojectId"))
        return "redirect:/view-projects";
    }

    @PostMapping("/edit-project")
    public String editProject(WebRequest userInput, RedirectAttributes redirectAttributes) {

        //parse RequestParam and use it to fetch model of the project to edit
        int parsedId = Integer.parseInt(userInput.getParameter("editProjectId"));
        Project editedProject = projectRepository.fetchProjectById(parsedId);
        System.out.println("Edited project id when fetched: " + editedProject.getId()); //debug

        //edit project model based on user input
        editedProject.setName(userInput.getParameter("editProjectName"));

        //send model project back to database
        projectRepository.editProject(editedProject); //debug
        System.out.println("Edited project id when fetched: " + editedProject.getId());
        //OBS. lidt i tvivl om paramName editProjectId
        redirectAttributes.addAttribute("projectId", userInput.getParameter("editProjectId"));

        return "redirect:/view-projects";
    }

    @GetMapping("/change-project-status")
    public String changeStatus(@RequestParam String id, @RequestParam String updatedStatus){

        //parsing the id as an int since we are receiving it as a String
        int parsedId = Integer.parseInt(id);
        //fetch the project in question
        Project editedProject = projectRepository.fetchProjectById(parsedId);
        //edit the projects's status
        editedProject.setStatus(updatedStatus);
        //update Project in database
        projectRepository.editProject(editedProject);
        System.out.println("Changed status to " + updatedStatus);
        return "redirect:/view-projects";
    }

    @GetMapping("/delete-project")
    public String deleteProject(@RequestParam String id, @RequestParam String ProjectId, RedirectAttributes redirectAttributes){

        //parsing the id as an int since we are receiving it as a String
        int parsedId = Integer.parseInt(id);

        projectRepository.deleteProject(parsedId);
        System.out.println("Deleted project: " + parsedId);

        redirectAttributes.addAttribute("projectId", ProjectId);

        return "redirect:/view-projects";
    }
}
