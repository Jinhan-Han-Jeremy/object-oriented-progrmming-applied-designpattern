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
        this.difficulty = difficulty;
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

    public static void main(String[] args) {
        // Task 객체 예시
        List<String> employees = List.of("ProjectManager", "BusinessOperator");
        List<String> requirements = List.of("프로젝트 목표와 범위 설정", "팀 구성 및 역할 할당");

        Task task = new Task("프로젝트 킥오프 미팅 주최 초기", employees, 4, requirements);

        System.out.println(task);
    }
}