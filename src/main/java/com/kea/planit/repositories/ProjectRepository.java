package com.kea.planit.repositories;

import com.kea.planit.models.Project;
import com.kea.planit.utilities.DBconnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectRepository {

    //Dummi class
    public static ArrayList<Project> getProjectList(){
        ArrayList<Project> projectList = new ArrayList<>();
        Project project1 = new Project(1,"Project 1", "Nice project1", 36,"Done", LocalDate.now());
        Project project2 = new Project(2,"Project 2", "Nice project2", 24,"Done", LocalDate.now());
        Project project3 = new Project(3,"Project 3", "Nice project3", 56,"Done", LocalDate.now());
        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);
        return projectList;
    }
    public ArrayList getAllProjects(int id) {
        try {
            Connection connection = DBconnector.getConnection();
            PreparedStatement ppst = connection.prepareStatement("SELECT * FROM projects WHERE id=" + id);
            ResultSet rs = ppst.executeQuery();
            ArrayList<Project> allProjectList = new ArrayList<>();
            while (rs.next()) {
                int projectid = rs.getInt(1);
                String projectname = rs.getString(2);
                int userid = rs.getInt(3);
                Project projectList = new Project(projectid, projectname, userid);
                allProjectList.add(projectList);
                System.out.println(projectList);
            }
            System.out.println("returned projectlist");
            return allProjectList;


        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
