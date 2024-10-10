package org.github.data;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.github.member.TeamMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TeamMemberDataManager extends TableDbConnector implements DataManagerStrategy {
    private List<TeamMember> teamMembers = new ArrayList<>();

    @Override
    public void parseData() {
        Connection connection = createConnection();
        if (connection == null) {
            System.out.println("DB 연결에 실패했습니다.");
            return;
        }

        String query = "SELECT name, role, level, state, skills_performance FROM team_member";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");

                // name이 null인 경우 현재 반복을 건너뛰고 다음으로 진행
                if (name == null) {
                    continue;
                }

                // role 컬럼 처리 (null 검사)
                String role = rs.getString("role");
                if (role == null) {
                    role = ""; // 기본값 설정
                }

                // level 컬럼 처리
                int level = rs.getInt("level");
                if (rs.wasNull()) {
                    level = 0; // null일 경우 기본값 설정
                }

                // state 컬럼 처리 (null 검사)
                Boolean state = rs.getBoolean("state");
                if (rs.wasNull()) {
                    state = null; // null일 경우 그대로 null로 처리
                }

                // skills_performance 컬럼 처리 (JSON 문자열)
                String json = rs.getString("skills_performance");
                Map<String, Integer> skillsPerformanceMap = new HashMap<>();
                if (json != null && !json.isEmpty()) {
                    // JSON 문자열을 HashMap으로 변환
                    skillsPerformanceMap = jsonToHashMap(json);
                }
                else{
                    skillsPerformanceMap = new HashMap<>();
                }

                // TeamMember 객체에 추가
                teamMembers.add(new TeamMember(name, role, level, state, skillsPerformanceMap));
            }

            System.out.println("team_member 테이블에서 데이터가 성공적으로 파싱되었습니다.");

        } catch (SQLException e) {
            System.out.println("데이터 파싱 중 오류가 발생했습니다.");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<TeamMember> getData() {
        return teamMembers;
    }

    // JSON 문자열을 HashMap으로 변환하는 메서드
    public static Map<String, Integer> jsonToHashMap(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Integer> map = new HashMap<>();
        try {
            // JSON 문자열을 HashMap<String, Integer>로 변환
            map = objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Integer.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {

        // TeamMemberDataManager 테스트
        System.out.println("\n=== TeamMemberDataManager 테스트 ===");
        TeamMemberDataManager teamMemberDataManager = new TeamMemberDataManager();
        teamMemberDataManager.parseData(); // 데이터베이스에서 데이터 파싱
        List<TeamMember> teamMembers = teamMemberDataManager.getData(); // 파싱한 데이터 가져오기
        System.out.println("Team Members:");
        for (TeamMember teamMember : teamMembers) {
            System.out.println(teamMember);
        }
    }
}