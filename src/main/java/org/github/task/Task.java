package org.github.task;


import java.util.List;

public class Task implements TaskInterface{
    private String name;  // 작업 이름
    private List<String> employees;  // 작업을 수행할 수 있는 직원들의 역할
    private int difficulty;  // 작업 난이도
    private List<String> requirements;  // 작업이 의존하는 이전 작업들

    // 생성자
    public Task(String name, List<String> employees, int difficulty, List<String> requirements) {

        this.name = name;
        this.employees = employees;

        if (difficulty < 1) {
            this.difficulty = difficulty * -1;
        } else if (difficulty > 5) {
            this.difficulty = 3;
        }
        else{
            this.difficulty = difficulty;
        }

        this.requirements = requirements;
    }

    // getter 메서드
    public String getName() {
        return name;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                ", difficulty=" + difficulty +
                ", requirements=" + requirements +
                '}';
    }

}