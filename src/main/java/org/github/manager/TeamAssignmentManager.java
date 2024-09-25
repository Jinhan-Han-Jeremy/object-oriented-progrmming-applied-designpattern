package org.github.manager;

import org.github.member.TeamMember;
import org.github.task.Task;

import java.util.List;
import java.util.Map;

public class TeamAssignmentManager implements TeamAssignmentManagerInterface{

    //각 멤버별 작업 시간을 행렬에 할당하는 함수
    private void populateMemberTaskTimes(double[][] timeMatrix, int memberIndex, List<Task> tasks, Map<String, Integer> evaluations) {
        for (int j = 0; j < tasks.size(); j++) {
            Task task = tasks.get(j);
            String taskName = task.getName();

            double taskTime = getTaskTime(evaluations, taskName);
            timeMatrix[memberIndex][j] = taskTime;
        }
    }

    // 특정 작업에 대한 시간 가져오기 함수
    private double getTaskTime(Map<String, Integer> evaluations, String taskName) {
        if (evaluations.containsKey(taskName)) {
            return evaluations.get(taskName);
        } else {
            return Double.POSITIVE_INFINITY; // 평가가 없으면 불가능한 작업으로 처리
        }
    }

    @Override
    public double[][] generateTimeMatrix(List<Task> selectedTasks, List<TeamMember> members) {
        // 시간 행렬 초기화 (팀 멤버 수 x 작업 수)
        double[][] timeMatrix = new double[members.size()][selectedTasks.size()];

        // 각 멤버가 작업을 완료하는 시간을 계산하여 timeMatrix에 저장
        for (int i = 0; i < members.size(); i++) {
            TeamMember member = members.get(i);
            Map<String, Integer> evaluations = member.getEvaluations(); // 각 멤버의 작업 평가 (작업당 소요 시간)

            populateMemberTaskTimes(timeMatrix, i, selectedTasks, evaluations);

        }
        return timeMatrix;
    }

    @Override
    public List<TeamMember> assignTasksToTeam(List<Task> tasks, List<TeamMember> members) {
        // 작업 할당 로직 구현 (예시: 최적의 팀원 조합을 찾아 작업에 할당)
        return null; // 실제 구현 필요
    }
}