package com.kea.planit.repositories;

import com.kea.planit.models.Project;
import com.kea.planit.models.Task;
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
    public ArrayList<Project> viewProject(int projectOwner) {
        ArrayList<Project> projectList = new ArrayList<>();
        try {
            PreparedStatement ppst = DBconnector.getConnection().prepareStatement("SELECT * FROM projects WHERE project_owner=" + projectOwner);
            ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                Project project = new Project(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("status"),
                        rs.getDate("deadline"),
                        rs.getInt("project_owner")
                );
                projectList.add(project);
            }
        }
        catch (SQLException e) {
            System.out.println("Something went wrong when fetching tasks from database");
            System.out.println(e.getMessage());
        }
        System.out.println("returned projectlist");
        return projectList;
    }

    public void addToProjectList(Project newProject) {

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("INSERT INTO projects VALUES (default,?,?,?,?)");
            preparedStatement.setString(1, newProject.getName());
            //preparedStatement.setDate(3, (Date) newProject.getDeadline());
            preparedStatement.setDate(2, Date.valueOf("2022-12-12")); //test
            preparedStatement.setInt(3, newProject.getProjectOwner());
            preparedStatement.setString(4, "Pending");
            preparedStatement.execute();
            System.out.println("Added project to database");
        } catch(SQLException exception) {
            System.out.println("Something went wrong when adding project to database");
            exception.printStackTrace();
        }
    }

    public void editTask0(Task taskToEdit) {

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement(
                    "UPDATE tasks SET name = ?, description = ?, hours = ?, deadline = ? WHERE task_owner = ?");
            preparedStatement.setString(1, taskToEdit.getName());
            preparedStatement.setString(2, taskToEdit.getDescription());
            preparedStatement.setInt(3, taskToEdit.getHours());
            //preparedStatement.setString(4, taskToEdit.getStatus());
            preparedStatement.setDate(4, Date.valueOf("2022-12-12")); //test
            preparedStatement.setInt(5, taskToEdit.getTaskOwner());
            preparedStatement.executeUpdate();
            System.out.println("Task edited");
        } catch (SQLException e) {
            System.out.println("Something went wrong when editing task");
            e.printStackTrace();
        }
    }

    //todo: find out why it edits all tasks in subproject at once
    public void editTask(Project editedProject) {

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement(
                    "UPDATE projects SET name = ?, description = ?, hours = ?, deadline = ? WHERE id = ?");
            preparedStatement.setString(1, editedProject.getName());
            //preparedStatement.setString(4, taskToEdit.getStatus());
            preparedStatement.setDate(2, Date.valueOf("2022-12-12")); //test because it's not working
            preparedStatement.setInt(3, editedProject.getId());
            preparedStatement.executeUpdate();
            System.out.println("Task edited (id: " + editedProject.getId() + ")"); //debug
        } catch (SQLException e) {
            System.out.println("Something went wrong when editing task");
            e.printStackTrace();
        }
    }

    public void deleteProject(int projectId) {
        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("DELETE FROM projects WHERE id = ?");
            preparedStatement.setInt(1, projectId);
            preparedStatement.execute();
            System.out.println("Deleted project");
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete project from database");
            e.printStackTrace();
        }
    }
}


