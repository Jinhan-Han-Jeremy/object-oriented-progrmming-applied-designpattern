package org.github.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskFactory {

    public static Task createTask(String name, int priorityNumber, String employees, int difficulty) {
        List<String> employeeList = Arrays.asList(employees.split(", "));
        return new Task(name, priorityNumber, employeeList, difficulty);
    }

    public static List<Task> createTasks() {
        List<Task> tasks = new ArrayList<>();

        tasks.add(createTask("프로젝트 목표와 범위 설정 초기", 2, "ProjectManager, TechLead", 4));

        return tasks;
    }
}