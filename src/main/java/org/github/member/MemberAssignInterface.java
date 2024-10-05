package org.github.member;

import org.github.task.Task;

import java.lang.reflect.Member;
import java.util.List;

public interface MemberAssignInterface {

    // 멤버를 작업에 할당하는 함수
    List<TeamMember> selectedMembersForTasks(List<Task> selectedTasks, List<TeamMember> members);

    // 특정 작업을 수행할 수 있는 멤버인지 확인하는 함수
    boolean canAddMemberForTasks(TeamMember member, List<Task> selectedTasks);

    // 멤버가 해당 작업에 적합한지 필터링하는 함수
    boolean addableMemberFilter(int taskDifficulty, int memberLevel);

    List<TeamMember> getAvailableMembers();
}