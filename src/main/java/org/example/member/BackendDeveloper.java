package org.example.member;

import java.util.List;

public class BackendDeveloper implements TeamMember{
    private String name;
    private String role;
    private List<String> skills;
    private int level;

    public BackendDeveloper(String name, String role, List<String> skills, int level) {
        this.name = name;
        this.role = role;
        this.skills = skills;
        this.level = level;
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
    public List getSkills() {
        return skills;
    }

    @Override
    public int getLevel(){
        return level;
    }

    @Override
    public float PerformanceRate(String work) {
        float PerformanceRate = 0;

        return PerformanceRate;
    }
}
