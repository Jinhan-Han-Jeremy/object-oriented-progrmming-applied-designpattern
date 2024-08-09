package org.example.member;

import java.awt.*;

public interface TeamMember {
    float PerformanceRate(String work);
    String getName();
    String getRole();
    int getLevel();
    List getSkills();
}
