package org.github.utility;

import org.github.member.TeamMember;
import org.github.task.Task;

import java.util.List;
import java.util.Map;

public interface UtilityEquationsInterface {
    float calculateMean(float total, int numberOfMembers);
    public List<Float> calculatePerformanceRates(List<TeamMember> members, List<Task> tasks, float totalDays);
    void StandardDeviationMaker(float mean);
    float getStandardDeviation();
    //List<TeamMember> multipleEquations(List<TeamMember> members);
    List<TeamMember> gridAlgorithm(List<TeamMember> members);

    // 멤버 리스트와 업무 리스트를 사용하여 연립방정식을 해결하는 함수
    UtilityEquations.AA multipleEquations(List<TeamMember> members, List<Task> tasks, float totalDays);
}
