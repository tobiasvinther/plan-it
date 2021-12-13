package com.kea.planit.repositories;

import com.kea.planit.models.Project;
import com.kea.planit.models.Task;
import com.kea.planit.utilities.DBconnector;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectRepository {


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

    public Project fetchProjectById(int id) {
        try {
            //String selectStatement = "SELECT * FROM projects WHERE id = ?";

            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("SELECT * FROM projects WHERE id = ?");

            preparedStatement.setString(1, String.valueOf(id));

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Project fetchedProject = new Project(
                        rs.getInt("id"), //id
                        rs.getString("name"), //name
                        rs.getString("status"), //status
                        rs.getDate("deadline"), //deadline
                        rs.getInt("project_owner") //projectOwner
                );
                System.out.println("Project (id: " + fetchedProject.getId() + ") fetched");
                return fetchedProject;
            }
        }
        catch(SQLException e){
            System.out.println("Something went wrong when fetching projects from database");
            System.out.println(e.getMessage());
        }
        System.out.println("Projects not fetched, returned null"); //debug
        return null;
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

    //todo: find out why it edits all tasks in subproject at once
    public void editProject(Project editedProject) {

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement(
                    "UPDATE projects SET name = ?, description = ?, hours = ?, deadline = ? WHERE id = ?");
            preparedStatement.setString(1, editedProject.getName());
            //preparedStatement.setString(4, projectToEdit.getStatus());
            preparedStatement.setDate(2, Date.valueOf("2022-12-12")); //test because it's not working
            preparedStatement.setInt(3, editedProject.getId());
            preparedStatement.executeUpdate();
            System.out.println("Project edited (id: " + editedProject.getId() + ")"); //debug
        } catch (SQLException e) {
            System.out.println("Something went wrong when editing project");
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


