package org.github;

import org.github.file.CSVFileReadException;
import org.github.file.CSVFormatException;
import org.github.file.TeamMemberManager;
import org.github.manipulator.OptimalTeam;
import org.github.member.MemberAssign;
import org.github.member.MemberAssignInterface;
import org.github.member.MemberTaskMatrix;
import org.github.member.TeamMember;
import org.github.task.Task;
import org.github.task.TaskAssign;
import org.github.tasksstream.TasksHistory;

import java.util.*;

public class Main {

    public static void hashMapSplitter(Map<String, Integer> hashMapStrInt){

        for (String key : hashMapStrInt.keySet()) {
            Integer value = hashMapStrInt.get(key);
            System.out.println("작업 : " + key + ", 값 : " + value);
        }
    }

    public static void main(String[] args) {

        List<TeamMember> members = new ArrayList<>();  // 팀 멤버 리스트
        List<Task> tasks = new ArrayList<>();  // 작업 리스트

        System.out.println("initial start");

        // 이프로 - Project Manager
        Map<String, Integer> evaluations1 = new HashMap<>();
        evaluations1.put("프로젝트 목표와 범위 설정 초기", 3);
        evaluations1.put("프로젝트 목표와 범위 설정 후기", 4);
        evaluations1.put("일정과 예산 계획 수립 초기", 4);
        evaluations1.put("일정과 예산 계획 수립 후기", 4);
        evaluations1.put("팀 구성 및 역할 할당 초기", 4);
        evaluations1.put("팀 구성 및 역할 할당 후기", 4);
        members.add(new TeamMember("이프로", "Project Manager", 2, true, new ArrayList<>(evaluations1.keySet()), evaluations1));

        // 최프로 - Project Manager
        Map<String, Integer> evaluations2 = new HashMap<>();
        evaluations2.put("프로젝트 목표와 범위 설정 초기", 3);
        evaluations2.put("프로젝트 목표와 범위 설정 후기", 3);
        evaluations2.put("일정과 예산 계획 수립 초기", 3);
        evaluations2.put("일정과 예산 계획 수립 후기", 4);
        members.add(new TeamMember("최프로", "Project Manager", 3, true, new ArrayList<>(evaluations2.keySet()), evaluations2));

        // 이덕마 - Product Manager
        Map<String, Integer> evaluations3 = new HashMap<>();
        evaluations3.put("팀 구성 및 역할 할당 후기", 4);
        evaluations3.put("프로젝트 킥오프 미팅 주최 초기", 5);
        evaluations3.put("서비스 수익 분석 초기", 3);
        evaluations3.put("프로젝트 킥오프 미팅 주최 후기", 5);
        evaluations3.put("서비스 수익 분석 후기", 3);
        members.add(new TeamMember("이덕마", "Product Manager", 2, false, new ArrayList<>(evaluations3.keySet()), evaluations3));

        // 최덕마 - Product Manager
        Map<String, Integer> evaluations4 = new HashMap<>();
        evaluations4.put("팀 구성 및 역할 할당 초기", 3);
        evaluations4.put("팀 구성 및 역할 할당 후기", 3);
        evaluations4.put("프로젝트 킥오프 미팅 주최 초기", 4);
        evaluations4.put("프로젝트 킥오프 미팅 주최 후기", 4);
        evaluations4.put("서비스 수익 분석 후기", 4);
        evaluations4.put("서비스 수익 분석 초기", 4);
        members.add(new TeamMember("최덕마", "Product Manager", 3, true, new ArrayList<>(evaluations4.keySet()), evaluations4));

        // 이비례 - Business Operator
        Map<String, Integer> evaluations5 = new HashMap<>();
        evaluations5.put("비즈니스 요구사항 분석 초기", 4);
        evaluations5.put("비즈니스 요구사항 분석 후기", 4);
        evaluations5.put("서비스 수익 분석 초기", 3);
        evaluations5.put("프로젝트 킥오프 미팅 주최 후기", 5);
        evaluations5.put("서비스 수익 분석 후기", 3);
        members.add(new TeamMember("이비례", "Business Operator", 2, true, new ArrayList<>(evaluations5.keySet()), evaluations5));

        // 유태리 - Tech Lead
        Map<String, Integer> evaluations6 = new HashMap<>();
        evaluations6.put("기술 스택 선정 초기", 4);
        evaluations6.put("시스템 아키텍처 설계 초기", 4);
        evaluations6.put("RESTful 서비스 초기", 4);
        evaluations6.put("마이크로서비스 초기", 4);
        members.add(new TeamMember("유태리", "Tech Lead", 3, true, new ArrayList<>(evaluations6.keySet()), evaluations6));

        List<String> employees = List.of("ProjectManager", "BusinessOperator", "Product Manager");



        // 데이터를 Task 객체로 생성하여 리스트에 추가
        tasks.add(new Task("프로젝트 목표와 범위 설정 후기", Arrays.asList("ProjectManager"), 4, Arrays.asList("")));
        tasks.add(new Task("일정과 예산 계획 수립 초기", Arrays.asList("ProjectManager", "ProductManager"), 3, Arrays.asList("프로젝트 목표와 범위 설정 초기")));
        tasks.add(new Task("일정과 예산 계획 수립 후기", Arrays.asList("ProjectManager", "ProductManager"), 3, Arrays.asList("프로젝트 목표와 범위 설정 초기")));
        tasks.add(new Task("팀 구성 및 역할 할당 초기", Arrays.asList("ProductManager"), 3, Arrays.asList("프로젝트 목표와 범위 설정 초기")));
        tasks.add(new Task("팀 구성 및 역할 할당 후기", Arrays.asList("ProductManager"), 4, Arrays.asList("프로젝트 목표와 범위 설정 초기")));
        tasks.add(new Task("프로젝트 킥오프 미팅 주최 초기", Arrays.asList("ProjectManager", "BusinessOperator"), 4, Arrays.asList("프로젝트 목표와 범위 설정 초기", "팀 구성 및 역할 할당 초기")));
        tasks.add(new Task("프로젝트 킥오프 미팅 주최 후기", Arrays.asList("ProjectManager", "BusinessOperator"), 5, Arrays.asList("프로젝트 목표와 범위 설정 초기", "팀 구성 및 역할 할당 초기")));
        tasks.add(new Task("서비스 수익 분석 초기", Arrays.asList("ProductManager"), 4, Arrays.asList("팀 구성 및 역할 할당 초기")));
        tasks.add(new Task("서비스 수익 분석 후기", Arrays.asList("ProductManager"), 5, Arrays.asList("팀 구성 및 역할 할당 초기")));
        tasks.add(new Task("시장 조사 및 경쟁 분석 초기", Arrays.asList("ProductManager"), 4, Arrays.asList("일정과 예산 계획 수립 초기", "팀 구성 및 역할 할당 초기")));
        tasks.add(new Task("시장 조사 및 경쟁 분석 후기", Arrays.asList("BusinessOperator"), 5, Arrays.asList("일정과 예산 계획 수립 초기", "팀 구성 및 역할 할당 초기")));
        tasks.add(new Task("사용자 요구사항 수집 및 분석 후기", Arrays.asList("ProductManager", "BusinessOperator"), 4, Arrays.asList("시장 조사 및 경쟁 분석 초기")));
        tasks.add(new Task("제품 비전과 전략 수립 초기", Arrays.asList("BusinessOperator", "ProductManager"), 4, Arrays.asList("서비스 수익 분석", "시장 조사 및 경쟁 분석")));
        tasks.add(new Task("제품 비전과 전략 수립 후기", Arrays.asList("BusinessOperator", "ProductManager"), 3, Arrays.asList("서비스 수익 분석", "시장 조사 및 경쟁 분석")));
        tasks.add(new Task("MVP 정의 초기", Arrays.asList("Developer"), 4, Arrays.asList("제품 비전과 전략 수립")));
        tasks.add(new Task("MVP 정의 후기", Arrays.asList("Developer"), 3, Arrays.asList("제품 비전과 전략 수립")));
        tasks.add(new Task("비즈니스 요구사항 분석 초기", Arrays.asList("ProductManager", "BusinessOperator"), 4, Arrays.asList("제품 비전과 전략 수립")));
        tasks.add(new Task("비즈니스 요구사항 분석 후기", Arrays.asList("ProductManager", "BusinessOperator"), 3, Arrays.asList("제품 비전과 전략 수립")));
        tasks.add(new Task("이해관계자 인터뷰 및 요구사항 문서화 초기", Arrays.asList("ProjectManager", "BusinessOperator"), 5, Arrays.asList("비즈니스 요구사항 분석  초기")));
        tasks.add(new Task("이해관계자 인터뷰 및 요구사항 문서화 후기", Arrays.asList("ProjectManager", "BusinessOperator"), 4, Arrays.asList("비즈니스 요구사항 분석  초기")));
        tasks.add(new Task("비즈니스 프로세스 정의 초기", Arrays.asList("BusinessOperator"), 4, Arrays.asList("비즈니스 요구사항 분석  초기")));
        tasks.add(new Task("비즈니스 프로세스 정의 후기", Arrays.asList("BusinessOperator"), 3, Arrays.asList("비즈니스 요구사항 분석  초기")));
        tasks.add(new Task("기술 스택 선정 초기", Arrays.asList("TechLead"), 2, Arrays.asList("비즈니스 요구사항 분석 초기")));
        tasks.add(new Task("기술 스택 선정 후기", Arrays.asList("TechLead"), 4, Arrays.asList("비즈니스 요구사항 분석  초기")));
        tasks.add(new Task("시스템 아키텍처 설계 초기", Arrays.asList("Developer", "TechLead"), 2, Arrays.asList("비즈니스 프로세스 정의 초기")));
        tasks.add(new Task("시스템 아키텍처 설계 후기", Arrays.asList("Developer", "TechLead"), 2, Arrays.asList("비즈니스 프로세스 정의 초기")));


        // List<TasksHistory> 생성 및 데이터 할당
        List<TasksHistory> tasksHistoryList = new ArrayList<>();

        tasksHistoryList.add(new TasksHistory(
                "프로젝트 목표와 범위 설정 초기",
                Arrays.asList("최프로", "이덕마", "이비례"),
                Arrays.asList("ProjectManager", "BusinessOperator"),
                3,
                "done",
                true
        ));

        tasksHistoryList.add(new TasksHistory(
                "일정과 예산 계획 수립 초기",
                Arrays.asList("이덕마"),
                Arrays.asList("ProjectManager", "ProductManager"),
                2,
                "done",
                true
        ));

        tasksHistoryList.add(new TasksHistory(
                "팀 구성 및 역할 할당 초기",
                Arrays.asList("이비례"),
                Arrays.asList("ProductManager"),
                2,
                "done",
                true
        ));

        tasksHistoryList.add(new TasksHistory(
                "서비스 수익 분석 초기",
                Arrays.asList("최덕마"),
                Arrays.asList("BusinessOperator"),
                3,
                "done",
                true
        ));

        tasksHistoryList.add(new TasksHistory(
                "시장 조사 및 경쟁 분석 초기",
                Arrays.asList("최덕마"),
                Arrays.asList("BusinessOperator"),
                3,
                "done",
                true
        ));

        tasksHistoryList.add(new TasksHistory(
                "제품 비전과 전략 수립 초기",
                Arrays.asList("최덕마", "최비례"),
                Arrays.asList("BusinessOperator", "ProductManager"),
                3,
                "done",
                true
        ));

        tasksHistoryList.add(new TasksHistory(
                "비즈니스 요구사항 분석 초기",
                Arrays.asList("이덕마", "이비례"),
                Arrays.asList("ProductManager", "BusinessOperator"),
                3,
                "progress",
                true
        ));

        tasksHistoryList.add(new TasksHistory(
                "MVP 정의 초기",
                Arrays.asList("유백데"),
                Arrays.asList("Developer"),
                0,
                "not started",
                false
        ));

        tasksHistoryList.add(new TasksHistory(
                "이해관계자 인터뷰 및 요구사항 문서화 초기",
                Arrays.asList("이프로", "이비례"),
                Arrays.asList("ProjectManager", "BusinessOperator"),
                0,
                "not started",
                false
        ));

        // 작업 추가 (작업 이름 예시)
        List<String> selectedTasknames = Arrays.asList("프로젝트 목표와 범위 설정", "일정과 예산 계획 수립", "팀 구성 및 역할 할당");

        List<Task> selectedTasks = new ArrayList<>();
        TaskAssign taskAssign = new TaskAssign(tasks, selectedTasknames, tasksHistoryList);
        selectedTasks = taskAssign.taskAssigner();
        ///여기까지 작업할당 완료
        ///여기까지 작업할당 완료


        System.out.println("\nsorted done ? ");
        for (int i = 0; i < selectedTasks.size(); i++) {

            System.out.println(selectedTasks.get(i).getName() + " difficulty : " + selectedTasks.get(i).getDifficulty());
        }

        System.out.println("Task Completion Times: " + 1);

        MemberAssign memberAssign= new MemberAssign(selectedTasks, members,  false);

        // 팀 멤버와 작업 정의
        List<TeamMember> selectedMembers = memberAssign.selectedMembersForTasks(selectedTasks, members);


        // 팀 멤버 출력
        for (int i =0; i < selectedMembers.size(); i++) {
            TeamMember member = selectedMembers.get(i);
            System.out.println(member.getName());

            Map<String, Integer> evaluations = member.getEvaluations();
            hashMapSplitter(evaluations);
        }

        List<String> selectedTaskNames = new ArrayList<>();

// selectedTasks에서 task 이름을 리스트에 추가
        for (int i = 0; i < selectedTasks.size(); i++) {
            selectedTaskNames.add(selectedTasks.get(i).getName());
            System.out.println(selectedTasks.get(i).getName());
        }

// 리스트를 String 배열로 변환
        String[] taskNamesArray = selectedTaskNames.toArray(new String[0]);

        MemberTaskMatrix memberTaskMatrix = new MemberTaskMatrix();
        double[][] timeMatrixx = memberTaskMatrix.generateTimeMatrix(selectedTasks, selectedMembers);

        System.out.println("\nStart optimization");
        for (int i = 0; i < timeMatrixx.length; i++) {
            for (int j = 0; j < timeMatrixx[i].length; j++) {
                System.out.print(timeMatrixx[i][j] + "\t");
            }
            System.out.println();  // 행이 끝날 때 줄바꿈
        }

        OptimalTeam optimalTeam = new OptimalTeam();
        for (int i = 0; i< selectedTasks.size(); i++){
            System.out.println("\nCase " + (i+1) + ": "+ selectedTasks.get(i).getName() + " - 작업 최적화 먼저");
            optimalTeam.findOptimalTeamCombination(selectedMembers, timeMatrixx, selectedTaskNames, i);
        }









        System.out.println("Starting Team Member Manager...");

        // Create an instance of TeamMemberManager using the singleton pattern
        TeamMemberManager manager = TeamMemberManager.INSTANCE;

        // Try to load team members from the CSV file
        try {
            manager.loadTeamMembers("C:\\Users\\USER\\IdeaProjects\\object-oriented-progrmming-applied-designpattern\\files\\team_member.csv");

            // Print all loaded team members
            System.out.println("startt ");
            for (TeamMember member : manager.getTeamMembers()) {
                System.out.println(member.getName());
            }
        } catch (CSVFileReadException | CSVFormatException e) {
            // Handle exceptions by printing error messages to the console
            System.err.println("An error occurred while loading team members: " + e.getMessage());
            e.printStackTrace(); // Optionally, log stack trace for debugging purposes
        }

        System.out.println("Team Member Manager has finished execution.");
    }
}