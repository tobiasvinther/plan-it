package com.kea.planit.services;

import com.kea.planit.models.Task;
import java.util.ArrayList;

//Author: Tobias Vinther

public class TaskService {

    public int calculateHours(ArrayList<Task> taskList) {
        int totalHours = 0;
        for (Task task : taskList) {
            totalHours += task.getHours();
        }
        return totalHours;
    }

    public int calculateCompletionPercentage(ArrayList<Task> taskList) {
        float numberOfDoneTasks = 0;
        for (Task task : taskList) {
           if(task.getStatus().equals("Done")) {
               numberOfDoneTasks++;
           }
       }
        int result = (int)((numberOfDoneTasks / taskList.size()) * 100);
        return result;
    }

}
