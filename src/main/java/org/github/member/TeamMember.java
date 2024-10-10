package org.github.member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamMember implements TeamMemberInterface {
    private String name;
    private String role;
    private int level;
    private List<String> skills;
    private boolean isWorking;
    private Map<String, Integer> evaluations;  // 평가를 저장하기 위한 맵

    public TeamMember(String name, String role, int level,  boolean isWorking, Map<String, Integer> evaluations) {
        this.name = name;
        this.role = role;
        this.level = level;
        this.isWorking = isWorking;

        // evaluations이 null일 경우 빈 HashMap으로 초기화, 그렇지 않으면 새로운 HashMap으로 복사하여 변경 가능하게 설정
        if (evaluations != null) {
            this.evaluations = new HashMap<>(evaluations);
        } else {
            this.evaluations = new HashMap<>();
        }
    }

    // 이름 반환
    public String getName() {
        return name;
    }

    // 역할 반환
    public String getRole() {
        return role;
    }

    // 레벨 반환
    public int getLevel() {
        return level;
    }

    // bool isWorking 반환
    public boolean getIsWorking() {
        return isWorking;
    }

    // isWorking 상태 변화
    public void setIsWorking(Boolean isWorking) {
        this.isWorking = isWorking;
    }

    // 특정 평가 점수 반환
    public Integer getEvaluation(String evaluationType) {
        return evaluations.getOrDefault(evaluationType, null);
    }

    // 특정 평가 점수 설정
    public void setEvaluation(String evaluationType, int score) {
        // 점수는 0에서 100 사이여야 함
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        evaluations.put(evaluationType, score);
    }

    // 모든 평가를 반환
    public Map<String, Integer> getEvaluations() {
        return evaluations;  // 방어적 복사로 반환
    }

    // 전체 평가 맵 설정
    public void setEvaluations(Map<String, Integer> newEvaluations) {
        if (newEvaluations == null) {
            throw new IllegalArgumentException("Evaluations map cannot be null.");
        }

        // 각 점수가 0에서 100 사이의 유효한 값인지 검사
        for (Map.Entry<String, Integer> entry : newEvaluations.entrySet()) {
            if (entry.getValue() < 0 || entry.getValue() > 100) {
                throw new IllegalArgumentException("All scores must be between 0 and 100.");
            }
        }

        // 유효한 평가 맵으로 설정
        this.evaluations = new HashMap<>(newEvaluations);
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", level=" + level +
                ", isWorking=" + isWorking +
                ", evaluations=" + evaluations +
                '}';
    }

    @Override
    public float getPerformanceRate(List<Integer> performamceScores) {
        return 0.0F;
    }

}