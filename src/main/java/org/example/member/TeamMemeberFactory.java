package org.example.member;

import java.util.List;

public class TeamMemberFactory {

    // BackendDeveloper 객체를 생성하는 팩토리 메서드
    public static TeamMembe createBackendDeveloper(String name, List<String> skills, int level) {
        String role = "Backend Developer"; // 역할을 "Backend Developer"로 설정
        return new TeamMembe(name, role, skills, level);
    }

    // 다른 팀원 종류도 생성하는 메서드를 추가할 수 있음
}