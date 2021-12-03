package com.kea.planit.services;

import com.kea.planit.models.Project;
import com.kea.planit.models.Task;

import java.util.ArrayList;

public class ProjectService {
    public int calculateProjectHours(ArrayList<Project> projectList) {
        int totalHours = 0;
        for (Project project : projectList) {
            totalHours += project.getHoursInAll();
        }
        return totalHours;
    }

}
