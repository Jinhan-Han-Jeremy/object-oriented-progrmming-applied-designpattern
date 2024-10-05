package org.github.utility;

import org.github.member.TeamMember;
import org.github.task.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UtilityEquationsInterface {
    float calculateMean(float total, int numberOfMembers);
    public List<Float> calculatePerformanceRates(List<TeamMember> members, List<Task> tasks, float totalDays);
    void StandardDeviationMaker(float mean);
    float getStandardDeviation();
    //List<TeamMember> multipleEquations(List<TeamMember> members);
    List<TeamMember> gridAlgorithm(List<TeamMember> members);

}
