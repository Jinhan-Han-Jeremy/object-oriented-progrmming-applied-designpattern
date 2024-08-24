package org.github.member;

import java.util.List;

public class ConcreteTeamMember implements TeamMember {
    private String name;
    private String role;
    private int level;
    private List<String> skills;

    public ConcreteTeamMember(String name, String role, int level, List<String> skills) {
        this.name = name;
        this.role = role;
        this.level = level;
        this.skills = skills;
    }

    @Override
    public float performanceRate(String work) {
        // 예시: 레벨에 따라 간단히 성과율을 계산
        return level * 1.2f;
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
        return skills;
    }
}