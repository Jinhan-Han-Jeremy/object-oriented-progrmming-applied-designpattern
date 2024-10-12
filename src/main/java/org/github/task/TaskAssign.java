package org.github.task;

import org.github.tasksstream.TasksHistory;

import java.util.*;
import org.github.utility.Utility;
import org.github.utility.SimilarityCalculator;

public class TaskAssign implements TaskAssignInterface{

    // 멤버 변수 선언
    private List<Task> tasks;
    private List<String> selectedTaskNames;
    private List<TasksHistory> tasksHistoryList;

    // 생성자
    public TaskAssign(List<Task> tasks, List<String> selectedTaskNames, List<TasksHistory> tasksHistoryList) {
        this.tasks = tasks;
        this.selectedTaskNames = selectedTaskNames;
        this.tasksHistoryList = tasksHistoryList;
    }

    private boolean checkTaskState(Task task) {
        for (TasksHistory tasksHistory : tasksHistoryList) {
            // task 이름과 TasksHistory 이름이 같고, state가 "done"인 경우 false 반환
            if (task.getName().equals(tasksHistory.getName()) && "done".equals(tasksHistory.getState())) {
                return false;
            }
        }
        // 조건에 해당하지 않으면 true 반환
        return true;
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public List<String> getSelectedTaskNames(){
        return selectedTaskNames;
    }

    public List<Task> getSelectedTasksByTaskNames(List<String> selectedTaskNames, List<Task> allTasks){
        Utility utils = new Utility();
        List<Task> foundTasks = new ArrayList<>();
        foundTasks = utils.getselectedTasksByNames(selectedTaskNames, allTasks);
        return foundTasks;
    }

    @Override
    public String removeWords(String taskName) {
        // " 초기"로 끝나면 " 초기" 제거
        if (taskName.endsWith(" 초기")) {
            taskName = taskName.substring(0, taskName.length() - 3);  // " 초기"의 길이는 3이므로 마지막 3글자를 제거
        }
        // " 후기"로 끝나면 " 후기" 제거
        else if (taskName.endsWith(" 후기")) {
            taskName = taskName.substring(0, taskName.length() - 3);  // " 후기"의 길이도 3이므로 마지막 3글자를 제거
        }
        return taskName;
    }

    @Override
    public List<Task> taskAssigner() {
        List<Task> selectedTasks = new ArrayList<>();
        Set<String> words = new HashSet<>();

        for (Task task : tasks) {
            processTask(task, words, selectedTasks);
        }

        selectedTasks.sort(Comparator.comparingInt(Task::getDifficulty).reversed());
        return selectedTasks;
    }

    // 1. Task 이름을 처리하고 상태를 확인하는 부분을 함수로 분리
    private void processTask(Task task, Set<String> words, List<Task> selectedTasks) {
        for (String selectedTaskName : selectedTaskNames) {
            String theWord = removeWords(task.getName());
            words.add(theWord);

            if (isTaskValid(task, theWord, selectedTaskName, words)) {
                System.out.println("Task is valid and similar: " + theWord);
                selectedTasks.add(task);
            }
        }
    }

    // 2. Task의 유효성을 확인하고 유사도 판단하는 함수로 분리
    private boolean isTaskValid(Task task, String theWord, String selectedTaskName, Set<String> words) {
        // 작업 상태를 체크하고 단어가 리스트에 있는지 확인
        if (checkTaskState(task) && words.contains(theWord)) {
            // 유사성 판단 결과 반환
            return SimilarityCalculator.isSimilar(selectedTaskName, theWord)
                    || SimilarityCalculator.containsOrContained(selectedTaskName, theWord);
        }
        return false;
    }
}