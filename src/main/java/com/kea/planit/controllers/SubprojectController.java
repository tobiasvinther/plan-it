package com.kea.planit.controllers;

import com.kea.planit.models.Subproject;
import com.kea.planit.repositories.ProjectRepository;
import com.kea.planit.repositories.SubprojectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;

@Controller
public class SubprojectController {

    SubprojectRepository subprojectRepository = new SubprojectRepository();
    ProjectRepository projectRepository = new ProjectRepository();

    @GetMapping("/view-subprojects")
    public String viewSubprojects(Model subprojectModel, @RequestParam String projectId){

        int parsedProjectId = Integer.parseInt(projectId);
        //get list of subproject for selected subproject
        subprojectModel.addAttribute("subprojectList", subprojectRepository.getSubprojectsInThisProject(parsedProjectId));
        subprojectModel.addAttribute("thisProjectId", parsedProjectId);
        subprojectModel.addAttribute("thisProject", projectRepository.fetchProjectById(parsedProjectId));
        //subprojectModel.addAttribute("thisProject", subprojectRepository.fetchProjectById(parsedProjectId));

        /*add the total hours and completion percentage to the model by using the service
        TaskService taskService = new TaskService();
        subprojectModel.addAttribute("totalHours", taskService.calculateHours(taskRepository.getTaskInThisSubproject(1))); //hardcoded for testing
        subprojectModel.addAttribute("completionPercentage", taskService.calculateCompletionPercentage(taskRepository.getTaskInThisSubproject(1))); //hardcoded for testing
        subprojectModel.addAttribute("statusCategories", taskService.getStatusCategories());
         */
        return "view-subprojects";
    }

    //todo: FINISH
    @PostMapping("/add-subproject")
    public String addSubproject(WebRequest userInput, RedirectAttributes redirectAttributes) {

        System.out.println("Project id for added subproject:" + userInput.getParameter("projectId"));
        //create a new subproject based on user input
        Subproject newSubproject = new Subproject(
                userInput.getParameter("newSubprojectName"),
                Date.valueOf("2022-12-12"), //hardcoded for testing purposes
                Integer.parseInt(userInput.getParameter("newSubprojectProjectId")) //hardcoded for testing purposes
        );

        subprojectRepository.addToSubprojectList(newSubproject);
        System.out.println("Subproject added: " + userInput.getParameter("newSubprojectName"));

        redirectAttributes.addAttribute("projectId", userInput.getParameter("newSubprojectProjectId"));

        return "redirect:/view-subprojects";
    }

    //todo: should be able to edit deadline
    @PostMapping("/edit-subproject")
    public String editSubproject(WebRequest userInput, RedirectAttributes redirectAttributes) {

        //parse RequestParam and use it to fetch model of the subproject to edit
        int parsedId = Integer.parseInt(userInput.getParameter("editSubprojectId"));
        Subproject editedSubproject = subprojectRepository.fetchSubprojectById(parsedId);
        System.out.println("Edited subproject id when fetched: " + editedSubproject.getId()); //debug

        //edit subproject model based on user input
        editedSubproject.setName(userInput.getParameter("editSubprojectName"));

        //send model subproject back to database
        subprojectRepository.editSubproject(editedSubproject); //debug
        System.out.println("Edited subproject id when fetched: " + editedSubproject.getId());

        redirectAttributes.addAttribute("projectId", userInput.getParameter("editSubprojectProjectId"));

        return "redirect:/view-subprojects";
    }

    @GetMapping("/delete-subproject")
    public String deleteSubproject(@RequestParam String id, @RequestParam String projectId, RedirectAttributes redirectAttributes){

        //parsing the id as an int since we are receiving it as a String
        int parsedId = Integer.parseInt(id);

        subprojectRepository.deleteSubproject(parsedId);
        System.out.println("Deleted subproject: " + parsedId);

        redirectAttributes.addAttribute("projectId", projectId);

        return "redirect:/view-subprojects";
    }

}
