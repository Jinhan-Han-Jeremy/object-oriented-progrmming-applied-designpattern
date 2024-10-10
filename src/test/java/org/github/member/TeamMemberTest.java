package org.github.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class TeamMemberTest {

    private static final Logger logger = Logger.getLogger(TeamMemberTest.class.getName());

    private TeamMember validMember;
    private TeamMember invalidMember;

    @BeforeEach
    public void setUp() {
        logger.info("테스트를 위한 TeamMember 객체 초기화");

        // 유효한 TeamMember 객체 초기화
        validMember = new TeamMember(
                "John Doe",
                "Developer",
                3,
                true,
                Map.of("Java", 90, "Python", 85)
        );

        // 레벨이 유효하지 않은 TeamMember 객체 초기화 (예시로 유효성 범위 1~5로 가정)
        invalidMember = new TeamMember(
                "Jane Doe",
                "Manager",
                6,  // 잘못된 레벨
                false,
                Map.of("Leadership", 95)
        );
        logger.info("유효하지 않은 TeamMember 객체 초기화 완료.");
    }

    @Test
    public void testValidTeamMember() {
        logger.info("testValidTeamMember() 실행 중...");
        // TeamMember 유효성 검증
        assertAll("Valid Member",
                () -> assertEquals("John Doe", validMember.getName(), "이름이 잘못되었습니다."),
                () -> assertEquals("Developer", validMember.getRole(), "역할이 잘못되었습니다."),
                () -> assertEquals(3, validMember.getLevel(), "레벨이 잘못되었습니다."),
                () -> assertTrue(validMember.getIsWorking(), "상태가 true여야 합니다."),
                () -> assertEquals(90, validMember.getEvaluation("Java"), "Java 평가 점수가 잘못되었습니다."),
                () -> assertEquals(85, validMember.getEvaluation("Python"), "Python 평가 점수가 잘못되었습니다.")
        );
        logger.info("testValidTeamMember() 성공.");
    }

    @Test
    public void testInvalidLevel() {
        logger.info("testInvalidLevel() 실행 중...");
        // 잘못된 레벨을 가진 TeamMember 객체에 대한 테스트
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidMember.getLevel() < 1 || invalidMember.getLevel() > 5) {
                throw new IllegalArgumentException("Level must be between 1 and 5.");
            }
        }, "레벨은 1~5 범위 내에 있어야 합니다.");
        logger.info("testInvalidLevel() 성공: 유효하지 않은 레벨을 검증.");
    }

    @Test
    public void testSetEvaluation() {
        logger.info("testSetEvaluation() 실행 중...");
        // 유효한 점수 설정 및 확인
        validMember.setEvaluation("Java", 60);
        assertEquals(60, validMember.getEvaluation("Java"), "Java 평가 점수가 잘못되었습니다.");

        // 유효하지 않은 점수 설정 시 예외 발생 여부 테스트
        assertThrows(IllegalArgumentException.class, () -> validMember.setEvaluation("Java", -10), "점수가 0 미만일 때 예외가 발생해야 합니다.");
        assertThrows(IllegalArgumentException.class, () -> validMember.setEvaluation("Python", 110), "점수가 100 초과일 때 예외가 발생해야 합니다.");

        logger.info("testSetEvaluation() 성공: Java 평가 점수 설정 확인.");
    }

    @Test
    public void testSetEvaluations() {
        logger.info("testSetEvaluations() 실행 중...");
        // 유효한 평가 맵 설정
        Map<String, Integer> newEvaluations = new HashMap<>();
        newEvaluations.put("JavaScript", 75);
        newEvaluations.put("C#", 85);
        validMember.setEvaluations(newEvaluations);

        // 설정된 평가 맵 확인
        Map<String, Integer> evaluations = validMember.getEvaluations();
        assertEquals(75, evaluations.get("JavaScript"), "JavaScript 평가 점수가 잘못되었습니다.");
        assertEquals(85, evaluations.get("C#"), "C# 평가 점수가 잘못되었습니다.");

        // 유효하지 않은 평가 맵 설정 시 예외 발생 여부 테스트
        Map<String, Integer> invalidEvaluations = new HashMap<>();
        invalidEvaluations.put("Go", -5);  // 유효하지 않은 점수
        assertThrows(IllegalArgumentException.class, () -> validMember.setEvaluations(invalidEvaluations), "점수가 0 미만일 때 예외가 발생해야 합니다.");

        logger.info("testSetEvaluations() 성공: 평가 맵 설정 확인.");
    }

    @Test
    public void testStateChange() {
        logger.info("testStateChange() 실행 중...");
        // 상태 변경 메서드 테스트
        validMember.setIsWorking(false);
        assertFalse(validMember.getIsWorking(), "상태가 false로 변경되어야 합니다.");
        logger.info("testStateChange() 성공: 상태 변경 확인.");
    }

    @Test
    public void testNullSkillsAndEvaluations() {
        logger.info("testNullSkillsAndEvaluations() 실행 중...");
        // Null값 처리에 대한 엣지 케이스 테스트
        TeamMember nullMember = new TeamMember("Null Member", "Tester", 2, true, null);
        assertNotNull(nullMember.getEvaluations(), "평가 맵이 null이면 안 됩니다.");
        assertTrue(nullMember.getEvaluations().isEmpty(), "평가 맵은 빈 상태여야 합니다.");
        logger.info("testNullSkillsAndEvaluations() 성공: null 처리 확인.");
    }
}