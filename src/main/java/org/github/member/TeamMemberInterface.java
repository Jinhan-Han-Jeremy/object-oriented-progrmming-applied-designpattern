package org.github.member;
import java.util.List;

public interface TeamMember {
    float performanceRate(String work);
    String getName();
    String getRole();
    int getLevel();
    List<String> getSkills();
}