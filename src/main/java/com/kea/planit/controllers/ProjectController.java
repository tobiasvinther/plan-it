package com.kea.planit.controllers;

import com.kea.planit.models.Project;
import com.kea.planit.repositories.ProjectRepository;
import com.kea.planit.repositories.TaskRepository;
import com.kea.planit.services.ProjectService;
import com.kea.planit.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.Date;

@Controller
public class ProjectController {
    //Instance singleton
    TaskRepository taskRepository = new TaskRepository();
    ProjectRepository projectRepository = new ProjectRepository();
    ProjectService projectService = new ProjectService();
    TaskService taskService = new TaskService();


    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/view-projects")
    public String viewProject(Model projectModel){
        //ArrayList<Project> projects = projectRepository.viewProject(1);
        /*for (Project project: projects) {
            project.setHoursInAll(taskService.calculateTaskHours(taskRepository.getTaskList(project.getId())));
        }*/

        projectModel.addAttribute("projectList", projectRepository.viewProject(1));
        //projectModel.addAttribute("taskList", taskRepository.getTaskList(id));
        return "view-projects";

    }
    @PostMapping("/add-project")
    public String addProject(WebRequest userInput) {
        //create a new project based on user input
        Project newProject = new Project(
                userInput.getParameter("newProjectName"),
                "Pending",
                Date.valueOf("2022-12-12"),
                1 //hardcoded for testing purposes
        );

        projectRepository.addToProjectList(newProject);
        System.out.println("Project added: " + userInput.getParameter("newProjectName"));
        //return "redirect:/view-all-wishes?wishlist_id=" + userInput.getParameter("wishlist_id");
        return "redirect:/view-projects";
    }

}
