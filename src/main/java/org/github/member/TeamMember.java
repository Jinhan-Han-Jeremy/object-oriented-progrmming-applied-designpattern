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

    public TeamMember(String name, String role, int level,  boolean isWorking, List<String> skills, Map<String, Integer> evaluations) {
        this.name = name;
        this.role = role;
        this.level = level;
        this.isWorking = isWorking;
        this.skills = new ArrayList<>(evaluations.keySet()); // List의 변경으로 인한 사이드 이펙트를 방지하기 위해 방어적 복사
        this.evaluations = new HashMap<>(evaluations);  // Map의 변경으로 인한 사이드 이펙트를 방지하기 위해 방어적 복사
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
    public boolean getisWorking() {
        return isWorking;
    }

    // 스킬 리스트 반환
    public List<String> getSkills() {
        return new ArrayList<>(skills);  // 방어적 복사로 반환
    }

    // 특정 평가 점수 반환
    public Integer getEvaluation(String evaluationType) {
        return evaluations.getOrDefault(evaluationType, null);
    }

    // 특정 평가 점수 설정
    public void setEvaluation(String evaluationType, int score) {
        evaluations.put(evaluationType, score);
    }


    public void setisWorking(String isWorking) {
        boolean result = true;
        if(isWorking.equals("progress")){
            result = false;
        }
    }

    // 모든 평가를 반환
    public Map<String, Integer> getEvaluations() {
        return new HashMap<>(evaluations);  // 방어적 복사로 반환
    }


    @Override
    public float getPerformanceRate(List<Integer> performamceScores) {
        return 0.0F;
    }

}