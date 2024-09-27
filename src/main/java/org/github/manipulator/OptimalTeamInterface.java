package org.github.manipulator;


import org.github.member.TeamMember;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public interface OptimalTeamInterface {
        // 각 팀원을 대상으로 최적의 팀을 찾는 메서드
        List<TeamMember> findOptimalTeamForTask2(List<TeamMember> teamMembers, double[][] timeMatrix, int taskIdx);

        // 조합된 팀을 찾고 작업 시간 출력하는 메서드
        void findOptimalTeamCombination2(List<TeamMember> teamMembers, double[][] timeMatrix, List<String> tasks, int firstTaskIdx);

        // 역함수를 이용한 합산을 계산하는 메서드
        BigDecimal calculateInverseSum(List<Double> times);

        // 역함수의 합을 기반으로 병렬 작업 시간을 계산하는 메서드
        BigDecimal calculateParallelTimeFromInverseSum(List<Double> times);

        // 주어진 팀원의 작업 시간을 계산하는 메서드
        BigDecimal calculateTaskTime2(List<TeamMember> teamMembers, List<TeamMember> selectedTeam, double[][] timeMatrix, int taskIdx);

        // 특정 작업에 할당된 팀원들을 제외하고 나머지 팀원을 반환하는 메서드
        List<TeamMember> excludeTeamMembers2(List<TeamMember> allMembers, List<TeamMember> selectedMembers);
    }