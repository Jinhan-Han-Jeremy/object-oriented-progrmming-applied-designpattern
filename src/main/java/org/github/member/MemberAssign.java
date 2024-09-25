package org.github.member;

import org.github.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberAssign implements MemberAssignInterface{

    @Override
    public List<TeamMember> selectMember(List<Task> selectedTasks, List<TeamMember> members, boolean userOrder) {
        List<TeamMember> selectedMembers = new ArrayList<>();

        for (TeamMember member : members) {
            boolean addable = canAddMemberForTasks(member, selectedTasks, userOrder); // 멤버가 작업을 수행할 수 있는지 확인

            if (addable && member.getState() == true) {
                selectedMembers.add(member); // 조건에 맞는 멤버를 추가
            }
        }
        return selectedMembers;
    }

    @Override
    public boolean canAddMemberForTasks(TeamMember member, List<Task> tasks, boolean userOrder) {
        Map<String, Integer> evaluations = member.getEvaluations(); // 각 멤버의 가능 작업 (작업당 소요 시간)

        for (Task task : tasks) {
            String taskName = task.getName();
            int taskDifficulty = task.getDifficulty();
            int memberLevel = member.getLevel();
            if (evaluations.containsKey(taskName) && addableMemberFilter(taskDifficulty, memberLevel, userOrder)) {
                return true; // 작업에 대한 수치가 있으면 true 반환
            }
        }
        return false;
    }

    @Override
    public boolean addableMemberFilter(int taskDifficulty, int memberLevel, boolean userOrder) {
        // 유저의 강제 할당 실행
        if (userOrder == true) {
            return true; // 난이도가 높고 멤버의 레벨이 1 이상이면 추가
        }
        // difficulty에 따른 적합한 레벨의 유저 할당
        if (taskDifficulty >= 3 && memberLevel > 1) {
            return true; // 난이도가 높고 멤버의 레벨이 1 이상이면 추가
        } else if (taskDifficulty < 3 && memberLevel < 3) {
            return true; // 난이도가 낮고 멤버의 레벨이 3 미만이면 추가
        }
        return false;
    }
}