package org.github.manager;


import org.github.member.TeamMember;
import org.github.task.Task;

import java.util.List;
import java.util.Map;

public interface TeamAssignmentManagerInterface {

    // 작업과 팀 멤버의 시간을 매트릭스로 생성
    double[][] generateTimeMatrix(List<Task> selectedTasks, List<TeamMember> members);

    // 추가적인 팀 할당 관련 메서드
    List<TeamMember> assignTasksToTeam(List<Task> tasks, List<TeamMember> members);
}
