package org.github.member;

import org.github.task.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class MemberTaskMatrix implements MemberTaskMatrixInterface{
    @Override
    public double[][] generateTimeMatrix(List<Task> selectedTasks, List<TeamMember> members) {
        // 시간 행렬 초기화 (팀 멤버 수 x 작업 수)
        double[][] timeMatrix = new double[members.size()][selectedTasks.size()];

        for (int i = 0; i < members.size(); i++) {
            TeamMember member = members.get(i);
            Map<String, Integer> evaluations = member.getEvaluations();

            fillMemberTimes(timeMatrix, i, selectedTasks, evaluations);
        }

        return timeMatrix;
    }

    // 1. 멤버별 작업 시간을 채우는 함수
    private void fillMemberTimes(double[][] timeMatrix, int memberIndex, List<Task> selectedTasks, Map<String, Integer> evaluations) {
        for (int j = 0; j < selectedTasks.size(); j++) {
            Task task = selectedTasks.get(j);
            timeMatrix[memberIndex][j] = getTaskTime(task, evaluations);
        }
    }

    // 2. 작업 시간 계산 함수
    private double getTaskTime(Task task, Map<String, Integer> evaluations) {
        String taskName = task.getName();

        if (evaluations.containsKey(taskName)) {
            return evaluations.get(taskName);
        } else {
            return Double.POSITIVE_INFINITY; // 평가가 없으면 불가능한 작업으로 처리
        }
    }


    /*
    // 4. 최적의 팀 조합을 업데이트하는 함수
    private void updateBestCombination(long count, int i, int j, int k, double time1, double time2, double time3, List<TeamMember> teamMembers, BigDecimal bestTime, List<TeamMember> bestCombination) {
        if (count == 0) {
            processAllFiniteCase(i, j, k, time1, time2, time3, teamMembers, bestTime, bestCombination);
        } else if (count == 1) {
            processOneInfiniteCase(i, j, k, time1, time2, time3, teamMembers, bestTime, bestCombination);
        } else if (count == 2) {
            processTwoInfiniteCase(i, j, k, time1, time2, time3, teamMembers, bestTime, bestCombination);
        }
    }

     */
}
