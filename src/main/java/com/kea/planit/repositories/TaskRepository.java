package com.kea.planit.repositories;

import com.kea.planit.models.Task;
import com.kea.planit.utilities.DBconnector;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Author: Tobias Vinther

public class TaskRepository {

    public ArrayList<Task> getTaskInThisSubproject(int taskOwner) {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            //todo: use setInt instead of concatenation
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("SELECT * FROM tasks WHERE task_owner = " + taskOwner);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Task task = new Task(
                        rs.getInt("id"), //id
                        rs.getString("name"), //name
                        rs.getString("description"), //description
                        rs.getInt("hours"), //hours
                        rs.getString("status"), //status
                        rs.getDate("deadline"), //deadline
                        rs.getInt("task_owner") //wishlist_id
                );
                taskList.add(task);
            }
        }
        catch(SQLException e){
            System.out.println("Something went wrong when fetching tasks from database");
            System.out.println(e.getMessage());
        }
        //todo: why does this fire three times?
        System.out.println("Returned task list"); //debug
        return taskList;
    }

    //NOT FINISHED
    public Task fetchTaskById(int id) {
        try {
            //String selectStatement = "SELECT * FROM tasks WHERE id = ?";

            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("SELECT * FROM tasks WHERE id = ?");

            preparedStatement.setString(1, String.valueOf(id));

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Task fetchedTask = new Task(
                        rs.getInt("id"), //id
                        rs.getString("name"), //name
                        rs.getString("description"), //description
                        rs.getInt("hours"), //hours
                        rs.getString("status"), //status
                        rs.getDate("deadline"), //deadline
                        rs.getInt("task_owner") //taskOwner
                );
                System.out.println("Task (id: " + fetchedTask.getId() + ") fetched");
                return fetchedTask;
            }
        }
        catch(SQLException e){
            System.out.println("Something went wrong when fetching tasks from database");
            System.out.println(e.getMessage());
        }
        System.out.println("Task not fetched, returned null"); //debug
        return null;
    }

    public void addToTaskList(Task newTask) {

        //String input = newTask.getDeadline().toString();
        //DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern( "yyyy/MM/dd" ) ;
        //LocalDate deadLineAsLocalDate = LocalDate.parse(input, dateFormatter) ;

        //LocalDate deadLineAsLocalDate = newTask.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("INSERT INTO tasks VALUES (default,?,?,?,?,?,?)"); //default because it auto increments
            preparedStatement.setString(1, newTask.getName());
            preparedStatement.setString(2, newTask.getDescription());
            preparedStatement.setInt(3, newTask.getHours());
            preparedStatement.setString(4, "Pending");
            //preparedStatement.setDate(5, (Date) newTask.getDeadline());
            //preparedStatement.setObject(5, deadLineAsLocalDate);
            preparedStatement.setDate(5, Date.valueOf("2022-12-12")); //test
            preparedStatement.setInt(6, newTask.getTaskOwner());
            preparedStatement.execute();
            System.out.println("Added task to database");
        } catch(SQLException exception) {
            System.out.println("Something went wrong when adding task to database");
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

    public void editTask(Task editedTask) {

        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement(
                    "UPDATE tasks SET name = ?, description = ?, hours = ?, deadline = ? WHERE id = ?");
            preparedStatement.setString(1, editedTask.getName());
            preparedStatement.setString(2, editedTask.getDescription());
            preparedStatement.setInt(3, editedTask.getHours());
            //preparedStatement.setString(4, taskToEdit.getStatus());
            preparedStatement.setDate(4, Date.valueOf("2022-12-12")); //test because it's not working
            preparedStatement.setInt(5, editedTask.getId());
            preparedStatement.executeUpdate();
            System.out.println("Task edited (id: " + editedTask.getId() + ")"); //debug
        } catch (SQLException e) {
            System.out.println("Something went wrong when editing task");
            e.printStackTrace();
        }
    }

    public void deleteTask(int taskId) {
        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("DELETE FROM tasks WHERE id = ?");
            preparedStatement.setInt(1, taskId);
            preparedStatement.execute();
            System.out.println("Deleted task");
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete task from database");
            e.printStackTrace();
        }
    }

}
