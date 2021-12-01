package com.kea.planit.repositories;

import com.kea.planit.models.Project;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectRepository {

    //Dummi class
    public ArrayList<Project> getProjectList(){
        ArrayList<Project> projectList = new ArrayList<>();
        Project project1 = new Project(1,"Project 1", "Nice project1", 36,"Done", LocalDate.now());
        Project project2 = new Project(2,"Project 2", "Nice project2", 24,"Done", LocalDate.now());
        Project project3 = new Project(3,"Project 3", "Nice project3", 56,"Done", LocalDate.now());
        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);
        return projectList;
    }
}
