package org.github.utility;

import org.github.data.DataManagerContext;
import org.github.data.DataManagerStrategy;
import org.github.member.TeamMember;
import org.github.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utility {

    // 제네릭 메서드를 사용하는 makingData 메서드
    public <T> List<T> makingData(DataManagerStrategy<T> dataManager) {
        // 제네릭 타입의 DataManagerContext 생성
        DataManagerContext<T> context = new DataManagerContext<>();

        // DataManagerStrategy를 전략으로 설정
        context.setStrategy(dataManager);

        // 데이터를 파싱 (DB에서 가져옴)
        context.executeParseData();

        // 파싱된 데이터를 가져오기
        List<T> dataList = context.getData();

        // 데이터 반환
        return dataList;
    }

    public List<String> getNamesOfTasks(List<Task> tasks){
        List<String> taskNames = new ArrayList<>();
        for (Task task : tasks){
            taskNames.add(task.getName());
        }
        return taskNames;
    }

    public List<Task> getselectedTasksByNames(List<String> taskNames, List<Task> tasks){
        List<Task> selectedTask = new ArrayList<>();
        for(String taskName : taskNames){
            for (Task task : tasks){
                if(task.getName().equals(taskName))
                    selectedTask.add(task);
            }
        }

        return selectedTask;
    }

    public TeamMember findTeamMemberByTaskName(List<TeamMember> teamMembers, String findingTaskName) {
        for (TeamMember member : teamMembers) {
            // skills_performance 맵에서 찾고자 하는 TaskName이 있는지 확인
            if (member.getEvaluations().containsKey(findingTaskName)) {
                // 키값이 일치하는 경우 해당 TeamMember 반환
                return member;
            }
        }
        // 찾지 못한 경우 null 반환
        return null;
    }

    public List<String> getNamesOfTeamMembers(List<TeamMember> team) {
        // 가변 리스트로 초기화
        List<String> names = new ArrayList<>();

        for (int i = 0; i < team.size(); i++) {
            names.add(team.get(i).getName());
        }

        return names;
    }

}
