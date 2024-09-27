package org.github.task;


import org.github.tasksstream.TasksHistory;

import java.util.List;
import java.util.Set;

public interface TaskAssignInterface {

    // 두 문자열 간의 유사도 계산
    double similarity(String s1, String s2);

    // 유사성이 75% 이상인지 확인
    boolean isSimilar(String selectedTaskNames, String theWord);

    // Levenshtein Distance 계산 메서드
    int levenshteinDistance(String s1, String s2);

    // Task와 List<TasksHistory>를 비교하는 함수
    boolean checkTaskState(Task task, List<TasksHistory> tasksHistoryList);

    // 문자열에서 특정 단어 제거하는 메서드
    String removeWords(String taskName);

    // 두 문자열 중 하나가 다른 하나를 포함하는지 확인하는 함수
    boolean containsOrContained(String selectedTaskName, String theWord);

    // 최종 작업 할당 메서드
    List<Task> taskAssigner(List<Task> tasks, List<String> selectedTaskNames, List<TasksHistory> tasksHistoryList);
}