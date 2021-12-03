package com.kea.planit.services;

import com.kea.planit.models.Task;

import java.util.ArrayList;

public class TaskService {
    public int calculateTaskHours(ArrayList<Task> taskList) {
        int totalHours = 0;
        for (Task task : taskList) {
            totalHours += task.getHours();
        }
        return totalHours;
    }

}
