package org.github.tasksstream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TasksHistoryTest {

    private static final Logger logger = Logger.getLogger(TasksHistoryTest.class.getName());
    private TasksHistory mockTasksHistory;

    @BeforeEach
    public void setUp() {
        logger.info("테스트를 위한 TasksHistory 객체 초기화.");

        // TasksHistory 객체를 Mocking
        mockTasksHistory = Mockito.mock(TasksHistory.class);

        // Mocking된 객체의 반환값 설정
        when(mockTasksHistory.getName()).thenReturn("Project Initialization");
        when(mockTasksHistory.getTeamMembers()).thenReturn(List.of("John", "Jane", "Doe"));
        when(mockTasksHistory.getAvailableJobs()).thenReturn(List.of("Developer", "Tester"));
        when(mockTasksHistory.getFinishedDays()).thenReturn(10);
        when(mockTasksHistory.getState()).thenReturn("in-progress");
        when(mockTasksHistory.isRequirementsSatisfied()).thenReturn(true);
    }

    @Test
    public void testGetName() {
        logger.info("testGetName() 실행 중...");
        // getName() 테스트
        String name = mockTasksHistory.getName();
        assertEquals("Project Initialization", name, "작업 이름이 잘못되었습니다.");
        verify(mockTasksHistory).getName(); // 메서드 호출 검증
        logger.info("testGetName() 성공: " + name);
    }

    @Test
    public void testGetTeamMembers() {
        logger.info("testGetTeamMembers() 실행 중...");
        // getTeamMembers() 테스트
        List<String> teamMembers = mockTasksHistory.getTeamMembers();
        assertEquals(List.of("John", "Jane", "Doe"), teamMembers, "팀 멤버 목록이 잘못되었습니다.");
        verify(mockTasksHistory).getTeamMembers(); // 메서드 호출 검증
        logger.info("testGetTeamMembers() 성공: " + teamMembers);
    }

    @Test
    public void testGetAvailableJobs() {
        logger.info("testGetAvailableJobs() 실행 중...");
        // getAvailableJobs() 테스트
        List<String> availableJobs = mockTasksHistory.getAvailableJobs();
        assertEquals(List.of("Developer", "Tester"), availableJobs, "가능한 작업 목록이 잘못되었습니다.");
        verify(mockTasksHistory).getAvailableJobs(); // 메서드 호출 검증
        logger.info("testGetAvailableJobs() 성공: " + availableJobs);
    }

    @Test
    public void testGetFinishedDays() {
        logger.info("testGetFinishedDays() 실행 중...");
        // getFinishedDays() 테스트
        int finishedDays = mockTasksHistory.getFinishedDays();
        assertEquals(10, finishedDays, "완료 일수가 잘못되었습니다.");
        verify(mockTasksHistory).getFinishedDays(); // 메서드 호출 검증
        logger.info("testGetFinishedDays() 성공: " + finishedDays);
    }

    @Test
    public void testGetState() {
        logger.info("testGetState() 실행 중...");
        // getState() 테스트
        String state = mockTasksHistory.getState();
        assertEquals("in-progress", state, "작업 상태가 잘못되었습니다.");
        verify(mockTasksHistory).getState(); // 메서드 호출 검증
        logger.info("testGetState() 성공: " + state);
    }

    @Test
    public void testIsRequirementsSatisfied() {
        logger.info("testIsRequirementsSatisfied() 실행 중...");
        // isRequirementsSatisfied() 테스트
        boolean requirementsSatisfied = mockTasksHistory.isRequirementsSatisfied();
        assertTrue(requirementsSatisfied, "요구사항 충족 상태가 잘못되었습니다.");
        verify(mockTasksHistory).isRequirementsSatisfied(); // 메서드 호출 검증
        logger.info("testIsRequirementsSatisfied() 성공: " + requirementsSatisfied);
    }

    @Test
    public void testSetters() {
        logger.info("testSetters() 실행 중...");
        // Setter 메서드 테스트
        mockTasksHistory.setName("Task 1");
        mockTasksHistory.setTeamMembers(List.of("Alice", "Bob"));
        mockTasksHistory.setAvailableJobs(List.of("Manager"));
        mockTasksHistory.setFinishedDays(5);
        mockTasksHistory.setState("completed");
        mockTasksHistory.setRequirementsSatisfied(false);

        // Setter 호출 확인
        verify(mockTasksHistory).setName("Task 1");
        verify(mockTasksHistory).setTeamMembers(List.of("Alice", "Bob"));
        verify(mockTasksHistory).setAvailableJobs(List.of("Manager"));
        verify(mockTasksHistory).setFinishedDays(5);
        verify(mockTasksHistory).setState("completed");
        verify(mockTasksHistory).setRequirementsSatisfied(false);

        logger.info("testSetters() 성공: 모든 Setter 메서드 호출 확인.");
    }

    @Test
    public void testToString() {
        logger.info("testToString() 실행 중...");
        // toString() 메서드는 실제 객체로 테스트
        TasksHistory tasksHistory = new TasksHistory(
                "Project Initialization",
                List.of("John", "Jane", "Doe"),
                List.of("Developer", "Tester"),
                10,
                "in-progress",
                true
        );

        String expectedString = "TasksHistory{name='Project Initialization', teamMembers=[John, Jane, Doe], availableJobs=[Developer, Tester], finishedDays=10, state='in-progress', requirementsSatisfied=true}";
        assertEquals(expectedString, tasksHistory.toString(), "toString() 결과가 예상과 다릅니다.");

        logger.info("testToString() 성공: " + tasksHistory.toString());
    }
}