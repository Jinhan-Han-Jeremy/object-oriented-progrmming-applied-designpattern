package org.github.member;
import java.util.List;

public interface TeamMemberInterface {
    float performanceRate(String work);
    String getName();
    String getRole();
    int getLevel();
    List<String> getSkills();

    void addSkill(String skill);
    void setRole(String role);
    void setLevel(int level);  // 레벨을 설정하는 메서드 추가
}