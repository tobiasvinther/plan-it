package com.kea.planit.controllers;

import com.kea.planit.repositories.ProjectRepository;
import com.kea.planit.repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/view-project")
    public String viewProject(Model projectModel){
        ProjectRepository projectRepository = new ProjectRepository();
        projectModel.addAttribute("projectList", projectRepository.getProjectList());
        return "view-project";
    }

}
