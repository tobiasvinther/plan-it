package com.kea.planit.repositories;

import com.kea.planit.models.Project;
import com.kea.planit.utilities.DBconnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectRepository {

    //private static Object Date;
    private ProjectRepository projectRepository = new ProjectRepository();

    //Dummi class
    /*public static ArrayList<Project> getProjectList(){
        ArrayList<Project> projectList = new ArrayList<>();
        Project project1 = new Project(1,"Project 1", "Nice project1", 36,"Done", (java.util.Date) Date);
        Project project2 = new Project(2,"Project 2", "Nice project2", 24,"Done", (java.util.Date) Date);
        Project project3 = new Project(3,"Project 3", "Nice project3", 56,"Done", (java.util.Date) Date);
        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);
        return projectList;
    }*/
    public ArrayList viewProject(int id) {
        try {
            Connection connection = DBconnector.getConnection();
            PreparedStatement ppst = connection.prepareStatement("SELECT * FROM projects WHERE id=" + id);
            ResultSet rs = ppst.executeQuery();
            ArrayList<Project> allProjectList = new ArrayList<>();
            while (rs.next()) {
                int projectid = rs.getInt(1);
                String projectname = rs.getString(2);
                String projectdescription = rs.getString(3);
                int projecthoursinall = rs.getInt(4);
                String projectstatus = rs.getString(5);
                Date projectdeadline = rs.getDate(6);
                Project projectList = new Project(projectid, projectname, projectdescription, projecthoursinall, projectstatus, projectdeadline);
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
