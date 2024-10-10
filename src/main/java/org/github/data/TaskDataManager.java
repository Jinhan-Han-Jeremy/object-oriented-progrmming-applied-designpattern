package org.github.data;
import org.github.task.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskDataManager extends TableDbConnector implements DataManagerStrategy<Task> {
    private List<Task> tasks = new ArrayList<>();

    @Override
    public void parseData() {
        Connection connection = createConnection();
        if (connection == null) {
            System.out.println("DB 연결에 실패했습니다.");
            return;
        }

        String query = "SELECT name, employees, difficulty, requirements FROM tasks";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");

                // name이 null인 경우 현재 반복을 건너뛰고 다음으로 진행
                if (name == null) {
                    continue;
                }

                // employees 컬럼 처리
                List<String> employees = null;
                String employeesString = rs.getString("employees");
                if (employeesString != null) {  // rs.getString()이 null인지 검사
                    employees = Arrays.asList(employeesString.split(","));
                }

                // difficulty 컬럼 처리
                int difficulty = rs.getInt("difficulty");
                if (rs.wasNull()) {
                    difficulty = 0; // null일 경우 기본값으로 설정
                }

                // requirements 컬럼 처리
                List<String> requirements = null;
                String requirementsString = rs.getString("requirements");
                if (requirementsString != null) {  // rs.getString()이 null인지 검사
                    requirements = Arrays.asList(requirementsString.split(","));
                }
                else
                {
                    requirements = new ArrayList<>();
                }

                // Task 객체 생성 및 추가
                tasks.add(new Task(name, employees, difficulty, requirements));
            }
            System.out.println("tasks 테이블에서 데이터가 성공적으로 파싱되었습니다.");

        } catch (SQLException e) {
            System.out.println("데이터 파싱 중 오류가 발생했습니다.");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Task> getData() {
        return tasks;
    }

    public static void main(String[] args) {
        // TaskDataManager 테스트
        System.out.println("=== TaskDataManager 테스트 ===");
        TaskDataManager taskDataManager = new TaskDataManager();
        taskDataManager.parseData(); // 데이터베이스에서 데이터 파싱
        List<Task> tasks = taskDataManager.getData(); // 파싱한 데이터 가져오기
        System.out.println("Tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

}