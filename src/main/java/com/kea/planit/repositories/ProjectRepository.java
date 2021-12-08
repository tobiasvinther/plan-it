package com.kea.planit.repositories;

import com.kea.planit.models.Project;
import com.kea.planit.utilities.DBconnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectRepository {

    //private static Object Date;
    //private ProjectRepository projectRepository = new ProjectRepository();

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
    public ArrayList viewProject(int projectOwner) {
        ArrayList<Project> allProjectList = new ArrayList<>();
        try {
            PreparedStatement ppst = DBconnector.getConnection().prepareStatement("SELECT * FROM projects WHERE project_owner=" + projectOwner);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                Project project = new Project(
                        rs.getString("id"),
                        rs.getString("name"),
                        //rs.getString("description"),
                        //rs.getInt("hoursinall"),
                        //rs.getString("status"),
                        rs.getDate("deadline"),
                        rs.getInt("project_owner")
                );
                allProjectList.add(project);
            }
        }
        catch (SQLException e) {
            System.out.println("Something went wrong when fetching tasks from database");
            System.out.println(e.getMessage());
        }
        System.out.println("returned projectlist");
        return allProjectList;
    }

    public void addToProjectList(Project newProject) {

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("INSERT INTO projects VALUES (default,?,?,?,?)");
            preparedStatement.setString(1, newProject.getName());
            preparedStatement.setString(2, "Pending");
            //preparedStatement.setDate(3, (Date) newProject.getDeadline());
            preparedStatement.setDate(3, Date.valueOf("2022-12-12")); //test
            preparedStatement.setInt(4, newProject.getProjectOwner());
            preparedStatement.execute();
            System.out.println("Added project to database");
        } catch(SQLException exception) {
            System.out.println("Something went wrong when adding project to database");
            exception.printStackTrace();
        }
    }
}


