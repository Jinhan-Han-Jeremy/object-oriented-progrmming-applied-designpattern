package org.github.data;

import org.github.member.TeamMember;
import org.github.task.Task;
import org.github.utility.Trie;
import org.github.utility.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


public class InitialDataStream {
    private final String workPlans;
    private static List<Task> tasks;
    private List<TeamMember> teamMembers;

    private int orderOfRecommendedWords =-1;

    public InitialDataStream(String workPlans) {
        this.workPlans = workPlans;
    }

    public void makingTasks(){
        Utility utils = new Utility();

        // TaskDataManager 인스턴스 생성
        TaskDataManager<Task> taskDataManager = new TaskDataManager<>();
        tasks = utils.makingData(taskDataManager);
    }

    private List<Integer> getSelectedIndexsForSummarizedTaskHistories(){
        List<Integer> getSelectedIndexs = new ArrayList<>(Arrays.asList(2, 3, 4));
        return getSelectedIndexs;
    }

    public List<String> getSummarizedTaskHistories()
    {
        Utility utils = new Utility();
        makingTasks();
        List<String> analyzedTaskStream = new ArrayList<>();

        List<Integer> selectedIndexs = getSelectedIndexsForSummarizedTaskHistories();
        List<String> analyzedTaskHistories = getAnalizedTaskHistories();

        for(int i = 0; i < analyzedTaskHistories.size(); i++ ){
            String selectedTaskHistory = selectRecommendedWord(selectedIndexs.get(i),
                    getAnalizedTaskHistories().get(i));
            analyzedTaskHistories.set(i, selectedTaskHistory);
        }

        analyzedTaskStream = analyzedTaskHistories;

        return analyzedTaskStream;
    }

    public List<String> getAnalizedTaskHistories()
    {
        Utility utils = new Utility();
        makingTasks();
        List<String> summarizedTaskStream = new ArrayList<>();

        //tasks들의 모든 이름들
        summarizedTaskStream = utils.getNamesOfTasks(tasks);

        //summarizedTaskStream = match_workstream_to_task(workPlans, summarizedTaskStream);
        return summarizedTaskStream;
    }

    public List<String> getRecommendedWordsForTasksHistory(String word){
        Trie wordsProcessor = new Trie();

        List<String> parsedStreamWords;
        parsedStreamWords = wordsProcessor.extractedWords(word);

        makingTasks();

        for(Task task: tasks){
            wordsProcessor.wordAssign(task.getName());
        }

        List<String> recommendedWords = List.of();
        for(String parsedStreamWord : parsedStreamWords){
            List<String> recommendedWords2 = wordsProcessor.autoComplete(parsedStreamWord);

            recommendedWords.addAll(recommendedWords2);
        }
        //set로 변경하여 중복을 피하고
        Set<String> recommendedSets = new HashSet<>();

        recommendedSets.addAll(recommendedWords);
        recommendedWords = new ArrayList<>(recommendedSets);

        //다시 리스트로 변환하여 사용
        return recommendedWords;
    }

    public String selectRecommendedWord(int indexOfRecommendedWords, String word){
        List<String> selectedTaskNames = getRecommendedWordsForTasksHistory(word);
        orderOfRecommendedWords = indexOfRecommendedWords;
        String selectedTaskName = "";
        if(orderOfRecommendedWords > 0){
            selectedTaskName = selectedTaskNames.get(indexOfRecommendedWords);
        }

        return selectedTaskName;
    }

    public void insertSelectedRolesData(List<String> selectedTaskNames) {
        TableDbConnector connector = new TableDbConnector();
        Connection connection = connector.createConnection();

        if (connection == null) {
            System.out.println("DB 연결에 실패했습니다.");
            return;
        }

        // 선택된 역할을 가져옴
        List<String> rolesToInsert = availableRoles(selectedTaskNames);

        // 작업 계획(workPlans)은 전역 변수로 가정
        String workPlansString = String.join(",", workPlans);
        String rolesToInsertString = String.join(",", rolesToInsert);

        // ID 생성 (UUID를 사용하여 고유한 ID 생성)
        String generatedId = UUID.randomUUID().toString();

        // 데이터베이스에 삽입할 쿼리
        String insertQuery = "INSERT INTO workstream_info (id, workstream, available_jobs) VALUES (?, ?, ?)";

        // 데이터베이스에 삽입
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, generatedId); // ID 값 설정
            pstmt.setString(2, workPlansString); // workstream 값 설정
            pstmt.setString(3, rolesToInsertString); // available_jobs 값 설정
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("데이터가 성공적으로 삽입되었습니다. ID: " + generatedId);
            } else {
                System.out.println("데이터 삽입에 실패했습니다.");
            }
        } catch (SQLException e) {
            System.out.println("데이터 삽입 중 오류가 발생했습니다.");
            e.printStackTrace();
        } finally {
            connector.closeConnection(connection);
        }
    }

    private List<String> availableRoles(List<String> selectedTaskNames){
        Set<String> roles = new HashSet<>();
        for(Task task : tasks){
            for(String taskName : selectedTaskNames){
                if(!task.getName().equals(taskName)){
                    continue;
                }
                else{
                    roles.addAll(task.getEmployees());
                }
            }
        }
        // Set을 List로 변환
        List<String> rolesList = new ArrayList<>(roles);

        return rolesList;
    }
}
