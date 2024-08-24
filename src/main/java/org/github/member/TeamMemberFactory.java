package org.github.member;
import java.util.ArrayList;
import java.util.List;

public class TeamMemberFactory {

    public static TeamMemberInterface createTeamMember(String name, String role, int level, List<String> skills) {
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException("Level must be between 1 and 3.");
        }
        return new TeamMember(name, role, level, skills);
    }

    public static List<TeamMemberInterface> createTeam(String role, int numberOfMembers) {
        List<TeamMemberInterface> team = new ArrayList<>();
        for (int i = 0; i < numberOfMembers; i++) {
            int level = (i % 3) + 1;  // 1~3 레벨로 순환
            team.add(createTeamMember(role + " " + (i + 1), role, level, List.of("Skill A", "Skill B")));
        }
        return team;
    }
}