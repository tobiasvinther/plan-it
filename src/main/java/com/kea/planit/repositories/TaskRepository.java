package com.kea.planit.repositories;

import com.kea.planit.models.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskRepository {

    //dummy class so far
    public ArrayList<Task> getTaskList(int id) {
        ArrayList<Task> taskList = new ArrayList<>();
        Task task1 = new Task(1, "A task", "Just a task", 6, "Pending", LocalDate.now(), 1,1);
        Task task2 = new Task(2, "Another task", "Just a task", 12, "Pending", LocalDate.now(), 1, 1);
        Task task3 = new Task(3, "Yet another task", "Let's have a little longer description this time", 9, "Pending", LocalDate.now(), 1, 1);
        Task task4 = new Task(4, "again another task", "hello task", 5, "Pending", LocalDate.now(), 2, 1);
        Task task5 = new Task(5, "One more Task again", "Heey one more task", 8, "Pending", LocalDate.now(), 2, 1);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getProjectId() == id) {
                result.add(task);
            }
        }
        return result;
    }

}
