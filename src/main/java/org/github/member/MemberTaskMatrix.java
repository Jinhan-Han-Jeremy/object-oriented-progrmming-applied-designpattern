package org.github.member;

import org.github.task.Task;

import java.util.List;
import java.util.Map;

public class MemberTaskMatrix implements MemberTaskMatrixInterface{
    @Override
    public double[][] generateTimeMatrix(List<Task> selectedTasks, List<TeamMember> members) {

        // 시간 행렬 초기화 (팀 멤버 수 x 작업 수)
        double[][] timeMatrix = new double[members.size()][selectedTasks.size()];

        // 각 멤버가 작업을 완료하는 시간을 계산하여 timeMatrix에 저장
        for (int i = 0; i < members.size(); i++) {
            TeamMember member = members.get(i);
            Map<String, Integer> evaluations = member.getEvaluations(); // 각 멤버의 작업 평가 (작업당 소요 시간)

            for (int j = 0; j < selectedTasks.size(); j++) {
                Task task = selectedTasks.get(j);
                String taskName = task.getName();

                // 해당 작업에 대한 평가 점수가 있는지 확인
                if (evaluations.containsKey(taskName)) {
                    int time = evaluations.get(taskName);
                    timeMatrix[i][j] = time; // 작업 시간을 할당
                } else {
                    timeMatrix[i][j] = Double.POSITIVE_INFINITY; // 평가가 없으면 불가능한 작업으로 처리
                }
            }
        }
        return timeMatrix;
    }
}
