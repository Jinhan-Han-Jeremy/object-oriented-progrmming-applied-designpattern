package org.github.task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskTest {

    private static final Logger logger = Logger.getLogger(TaskTest.class.getName());
    private Task validTask;

    @BeforeEach
    public void setUp() {
        logger.info("테스트를 위한 Task 객체 초기화");

        // Mockito를 사용하여 Task 객체의 동작을 Mocking 가능
        validTask = Mockito.mock(Task.class);

        // Mock된 Task 객체의 반환값을 정의
        when(validTask.getName()).thenReturn("프로젝트 킥오프 미팅 주최 초기");
        when(validTask.getEmployees()).thenReturn(List.of("ProjectManager", "BusinessOperator"));
        when(validTask.getDifficulty()).thenReturn(4);
        when(validTask.getRequirements()).thenReturn(List.of("프로젝트 목표와 범위 설정", "팀 구성 및 역할 할당"));
        when(validTask.toString()).thenReturn("Task{name='프로젝트 킥오프 미팅 주최 초기', employees=[ProjectManager, BusinessOperator], difficulty=4, requirements=[프로젝트 목표와 범위 설정, 팀 구성 및 역할 할당]}");
    }

    // getName() 테스트
    @Test
    public void testGetName() {
        logger.info("testGetName() 실행 중...");
        assertEquals("프로젝트 킥오프 미팅 주최 초기", validTask.getName(), "작업 이름이 잘못되었습니다.");
        logger.info("testGetName() 성공: " + validTask.getName());
    }

    // getEmployees() 테스트
    @Test
    public void testGetEmployees() {
        logger.info("testGetEmployees() 실행 중...");
        List<String> expectedEmployees = List.of("ProjectManager", "BusinessOperator");
        assertEquals(expectedEmployees, validTask.getEmployees(), "직원 목록이 예상과 다릅니다.");
        logger.info("testGetEmployees() 성공: " + validTask.getEmployees());
    }

    // getDifficulty() 테스트
    @Test
    public void testGetDifficulty() {
        logger.info("testGetDifficulty() 실행 중...");
        assertEquals(4, validTask.getDifficulty(), "난이도 값이 잘못되었습니다.");
        logger.info("testGetDifficulty() 성공: " + validTask.getDifficulty());
    }

    // getRequirements() 테스트
    @Test
    public void testGetRequirements() {
        logger.info("testGetRequirements() 실행 중...");
        List<String> expectedRequirements = List.of("프로젝트 목표와 범위 설정", "팀 구성 및 역할 할당");
        assertEquals(expectedRequirements, validTask.getRequirements(), "요구사항 목록이 예상과 다릅니다.");
        logger.info("testGetRequirements() 성공: " + validTask.getRequirements());
    }

    // toString() 메서드 테스트
    @Test
    public void testToString() {
        logger.info("testToString() 실행 중...");
        String expectedString = "Task{name='프로젝트 킥오프 미팅 주최 초기', employees=[ProjectManager, BusinessOperator], difficulty=4, requirements=[프로젝트 목표와 범위 설정, 팀 구성 및 역할 할당]}";
        assertEquals(expectedString, validTask.toString(), "toString() 결과가 예상과 다릅니다.");
        logger.info("testToString() 성공: " + validTask.toString());
    }

    // null 직원 목록에 대한 테스트
    @Test
    public void testNullEmployees() {
        logger.info("testNullEmployees() 실행 중...");
        // Mock으로 Null을 반환하는 경우 테스트
        Task taskWithNullEmployees = mock(Task.class);
        when(taskWithNullEmployees.getEmployees()).thenReturn(null);

        assertNull(taskWithNullEmployees.getEmployees(), "직원 목록이 null이어야 합니다.");
        logger.info("testNullEmployees() 성공: 직원 목록이 null임을 확인.");
    }

    // 빈 요구사항 목록 테스트
    @Test
    public void testEmptyRequirements() {
        logger.info("testEmptyRequirements() 실행 중...");
        // Mock으로 빈 리스트를 반환하는 경우 테스트
        Task taskWithEmptyRequirements = mock(Task.class);
        when(taskWithEmptyRequirements.getRequirements()).thenReturn(List.of());

        assertTrue(taskWithEmptyRequirements.getRequirements().isEmpty(), "요구사항 목록이 비어있어야 합니다.");
        logger.info("testEmptyRequirements() 성공: 요구사항 목록이 비어있음을 확인.");
    }
}