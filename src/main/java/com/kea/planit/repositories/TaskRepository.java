package com.kea.planit.repositories;

import com.kea.planit.models.Task;
import com.kea.planit.utilities.DBconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

//Author: Tobias Vinther

public class TaskRepository {

    //private ArrayList<Task> taskList = new ArrayList<>();

    /*dummy class so far
    public void populateTaskList() {
        //ArrayList<Task> taskList = new ArrayList<>();
        Task task1 = new Task(1, "A task", "Just a task", 6, "Pending", LocalDate.now()), 1;
        Task task2 = new Task(2, "Another task", "Just a task", 12, "Done", LocalDate.now()), 1;
        Task task3 = new Task(3, "Yet another task", "Let's have a little longer description this time", 9, "Pending", LocalDate.now()), 1;
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
    }
     */

    public ArrayList<Task> getTaskInThisSubproject(int taskOwner) {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
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
        System.out.println("Returned task list"); //debug
        return taskList;
    }

    public void deleteTask(int taskId) {
        try {
            PreparedStatement preparedStatement = DBconnector.getConnection().prepareStatement("DELETE FROM tasks WHERE id = " + taskId);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete task from database");
            e.printStackTrace();
        }
    }

    //add task dummy
    public void addToTaskList(Task newTask) {
        //taskList.add(newTask);
    }

    //testing
    /*
    public void printTaskList() {
        for (Task task : taskList) {
            System.out.println(task.getName());
        }
    }
     */

}
