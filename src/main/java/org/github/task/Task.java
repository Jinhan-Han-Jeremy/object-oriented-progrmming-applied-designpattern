package org.github.task;

import org.github.task.TaskInterface;
import java.util.List;

public class Task implements TaskInterface {

    private String name; // 업무 이름
    private int priorityNumber;
    private List<String> employees; // 이 업무를 수행할 수 있는 직원들
    private int difficulty; // 업무의 난이도

    public Task(String name, int priorityNumber, List<String> employees, int difficulty) {
        this.priorityNumber = priorityNumber;
        this.name = name;
        this.employees = employees;
        this.difficulty = difficulty;
    }

    @Override
    public int getPriorityNumber() {
        return priorityNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getEmployees() {
        return employees;
    }

    @Override
    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public boolean canBeHandledBy(String employeeRole) {
        return employees.contains(employeeRole);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                ", difficulty=" + difficulty +
                '}';
    }
}