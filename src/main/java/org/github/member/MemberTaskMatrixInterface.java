package org.github.member;

import org.github.task.Task;

import java.util.List;

public interface MemberTaskMatrixInterface {

    public double[][] generateTimeMatrix(List<Task> selectedTasks, List<TeamMember> members);
}
