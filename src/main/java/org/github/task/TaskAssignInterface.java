package org.github.task;


import org.github.tasksstream.TasksHistory;

import java.util.List;
import java.util.Set;

public interface TaskAssignInterface {

    // 문자열에서 특정 단어 제거하는 메서드
    String removeWords(String taskName);


    // 최종 작업 할당 메서드
    List<Task> taskAssigner();
}