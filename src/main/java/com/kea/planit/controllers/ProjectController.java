package com.kea.planit.controllers;

import com.kea.planit.models.Project;
import com.kea.planit.repositories.ProjectRepository;
import com.kea.planit.repositories.TaskRepository;
import com.kea.planit.services.ProjectService;
import com.kea.planit.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

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
    @GetMapping("/view-project")
    public String viewProject(Model projectModel){
        ArrayList<Project> projects = projectRepository.getProjectList();
        for (Project project: projects) {
            project.setHoursInAll(taskService.calculateTaskHours(taskRepository.getTaskList()));
        }

        projectModel.addAttribute("projectList", projects);
        projectModel.addAttribute("taskList", taskRepository.getTaskList());
        //Add total hour calculation to all projects.
        projectModel.addAttribute("totalProjectsHours", projectService.calculateProjectHours(projectRepository.getProjectList()));
        //Add total hour calculation to each projects.
        projectModel.addAttribute("totalTaskHours", taskService.calculateTaskHours(taskRepository.getTaskList()));
        return "view-project";

    }


}
