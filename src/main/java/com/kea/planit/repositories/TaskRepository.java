package com.kea.planit.repositories;

import com.kea.planit.models.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskRepository {

    //dummy class so far
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> taskList = new ArrayList<>();
        Task task1 = new Task(1, "A task", "Just a task", 6, "Pending", LocalDate.now());
        Task task2 = new Task(2, "Another task", "Just a task", 12, "Pending", LocalDate.now());
        Task task3 = new Task(3, "Yet another task", "Let's have a little longer description this time", 9, "Pending", LocalDate.now());
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        return taskList;
    }

}
