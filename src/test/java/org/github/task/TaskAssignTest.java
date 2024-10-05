package org.github.task;
import org.github.tasksstream.TasksHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskAssignTest {

    private static final Logger logger = Logger.getLogger(TaskAssignTest.class.getName());

    private TaskAssign taskAssign;
    private List<Task> tasks;
    private List<String> selectedTaskNames;
    private List<TasksHistory> tasksHistoryList;

    @BeforeEach
    public void setUp() {
        logger.info("테스트를 위한 TaskAssign 객체 초기화");

        // Task 객체들 생성 (Mock 사용 가능)
        Task task1 = mock(Task.class);
        when(task1.getName()).thenReturn("프로젝트 목표 설정 초기");
        when(task1.getDifficulty()).thenReturn(3);

        Task task2 = mock(Task.class);
        when(task2.getName()).thenReturn("기술 설계 후기");
        when(task2.getDifficulty()).thenReturn(4);

        tasks = List.of(task1, task2);
        selectedTaskNames = List.of("프로젝트 목표 설정", "기술 설계");

        // TasksHistory 객체들 생성
        TasksHistory tasksHistory1 = mock(TasksHistory.class);
        when(tasksHistory1.getName()).thenReturn("프로젝트 목표 설정 초기");
        when(tasksHistory1.getState()).thenReturn("done");

        TasksHistory tasksHistory2 = mock(TasksHistory.class);
        when(tasksHistory2.getName()).thenReturn("기술 설계 후기");
        when(tasksHistory2.getState()).thenReturn("in-progress");

        tasksHistoryList = List.of(tasksHistory1, tasksHistory2);

        // TaskAssign 객체 생성
        taskAssign = new TaskAssign(tasks, selectedTaskNames, tasksHistoryList);

    }

    @Test
    public void testRemoveWords() {
        logger.info("testRemoveWords() 실행 중...");

        // " 초기"와 " 후기"가 제거되는지 확인
        assertEquals("프로젝트 목표 설정", taskAssign.removeWords("프로젝트 목표 설정 초기"), "초기 단어 제거 실패");
        assertEquals("기술 설계", taskAssign.removeWords("기술 설계 후기"), "후기 단어 제거 실패");

        logger.info("testRemoveWords() 성공: 초기 및 후기 단어 제거 확인.");
    }

    @Test
    public void testTaskAssigner() {
        logger.info("testTaskAssigner() 실행 중...");

        // taskAssigner() 메서드 테스트
        List<Task> assignedTasks = taskAssign.taskAssigner();

        assertNotNull(assignedTasks, "할당된 작업 리스트가 null입니다.");
        assertEquals(1, assignedTasks.size(), "할당된 작업 수가 잘못되었습니다.");
        assertEquals("기술 설계 후기", assignedTasks.get(0).getName(), "잘못된 작업이 할당되었습니다.");

        logger.info("testTaskAssigner() 성공: 작업 할당 확인.");
    }
}