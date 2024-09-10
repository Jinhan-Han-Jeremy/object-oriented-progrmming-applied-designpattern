package org.github.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskFactory {

    // Task를 생성하는 메서드: employees와 requirements는 각각 List<String> 형식으로 받음
    public static Task createTask(String name, List<String> employees, int difficulty, List<String> requirements) {
        return new Task(name, employees, difficulty, requirements);
    }


    // 여러 Task를 생성하는 메서드
    public static List<Task> createTasks() {
        List<Task> tasks = new ArrayList<>();

        // Task 데이터 추가
        tasks.add(createTask("프로젝트 목표와 범위 설정 초기",
                Arrays.asList("ProjectManager", "BusinessOperator"),
                4,
                new ArrayList<>()));

        tasks.add(createTask("프로젝트 목표와 범위 설정 후기",
                Arrays.asList("ProjectManager"),
                4,
                new ArrayList<>()));

        tasks.add(createTask("일정과 예산 계획 수립 초기",
                Arrays.asList("ProjectManager", "ProductManager"),
                3,
                Arrays.asList("프로젝트 목표와 범위 설정")));

        tasks.add(createTask("일정과 예산 계획 수립 후기",
                Arrays.asList("ProjectManager", "ProductManager"),
                4,
                Arrays.asList("프로젝트 목표와 범위 설정")));

        tasks.add(createTask("팀 구성 및 역할 할당 초기",
                Arrays.asList("ProductManager"),
                3,
                Arrays.asList("프로젝트 목표와 범위 설정")));

        tasks.add(createTask("팀 구성 및 역할 할당 후기",
                Arrays.asList("ProductManager"),
                4,
                Arrays.asList("프로젝트 목표와 범위 설정")));

        tasks.add(createTask("프로젝트 킥오프 미팅 주최 초기",
                Arrays.asList("ProjectManager", "BusinessOperator"),
                4,
                Arrays.asList("프로젝트 목표와 범위 설정", "팀 구성 및 역할 할당")));

        return tasks;
    }

    // 메인 메서드 - TaskFactory 사용 예시
    public static void main(String[] args) {
        List<Task> tasks = createTasks();

        // 생성된 Task 출력
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}