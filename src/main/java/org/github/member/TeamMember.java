package org.github.member;

import java.util.ArrayList;
import java.util.List;

public class TeamMember implements TeamMemberInterface {
    private String name;
    private String role;
    private int level;
    private List<String> skills;

    public TeamMember(String name, String role, int level, List<String> skills) {
        this.name = name;
        this.role = role;
        this.level = level;
        this.skills = new ArrayList<>(skills);  // List의 변경으로 인한 사이드 이펙트를 방지하기 위해 방어적 복사
    }

    @Override
    public float performanceRate(String work) {
        // 성과율 계산 로직을 더 구체화
        // 예: 각 업무에 따라 다른 성과율을 계산할 수 있도록 개선 가능
        float baseRate = 1.2f;
        switch (work.toLowerCase()) {
            case "development":
                return baseRate * level * 1.5f;
            case "design":
                return baseRate * level * 1.3f;
            default:
                return baseRate * level;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public List<String> getSkills() {
        return new ArrayList<>(skills); // 외부에서 List를 변경하지 못하도록 방어적 복사
    }

    @Override
    public void addSkill(String skill) {
        if (!skills.contains(skill)) {
            skills.add(skill);
        }
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }
}