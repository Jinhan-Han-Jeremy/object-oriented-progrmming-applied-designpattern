package org.github.file;
import org.github.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskParser implements CSVParser<Task> {

    @Override
    public List<Task> parse(List<String[]> data) {
        List<Task> tasks = new ArrayList<>();

        // 첫 번째 행이 헤더인지 확인
        boolean isFirstRow = true;
        int nameIndex = -1, employeesIndex = -1, difficultyIndex = -1, requirementsIndex = -1;

        for (String[] row : data) {
            if (isFirstRow) {
                // 첫 번째 행을 헤더로 처리하여 각 인덱스를 매핑
                for (int i = 0; i < row.length; i++) {
                    String column = row[i].trim().toLowerCase();
                    switch (column) {
                        case "name":
                            nameIndex = i;
                            break;
                        case "employees":
                            employeesIndex = i;
                            break;
                        case "difficulty":
                            difficultyIndex = i;
                            break;
                        case "requirements":
                            requirementsIndex = i;
                            break;
                        default:
                            System.err.println("Unknown column: " + column);
                            break;
                    }
                }
                isFirstRow = false;
                continue;
            }

            // 필수 인덱스 확인
            if (nameIndex == -1 || employeesIndex == -1 || difficultyIndex == -1) {
                throw new IllegalArgumentException("CSV file must contain 'name', 'employees', and 'difficulty' columns.");
            }

            // 데이터가 충분하지 않은 행을 건너뜀
            if (row.length < Math.max(nameIndex, Math.max(employeesIndex, difficultyIndex)) + 1) {
                System.err.println("Warning: Skipping incomplete row: " + Arrays.toString(row));
                continue;
            }

            try {
                // 각 칼럼의 데이터 가져오기
                String name = row[nameIndex].trim();
                String employeesField = row[employeesIndex].replace("\"", "").trim();
                List<String> employees = employeesField.isEmpty() ? new ArrayList<>() : new ArrayList<>(Arrays.asList(employeesField.split(",\\s*")));

                // difficulty가 숫자인지 확인
                String difficultyStr = row[difficultyIndex].trim();
                int difficulty = 0;

                // 만약 difficulty가 숫자가 아니면, employees로 처리
                if (!difficultyStr.matches("\\d+")) {
                    System.err.println("Warning: Invalid difficulty value '" + difficultyStr + "' for task '" + name + "'. Adding to employees.");
                    employees.add(difficultyStr);  // 숫자가 아니면 employees 리스트에 추가
                    if (row.length > difficultyIndex + 1) {
                        // difficulty를 다음 칼럼 값으로 설정
                        difficultyStr = row[difficultyIndex + 1].trim();
                        if (difficultyStr.matches("\\d+")) {
                            difficulty = Integer.parseInt(difficultyStr);
                        } else {
                            System.err.println("Error: Still unable to find valid difficulty for task '" + name + "'.");
                            continue; // 제대로 된 difficulty 값을 찾지 못하면 행을 건너뜀
                        }
                    }
                } else {
                    difficulty = Integer.parseInt(difficultyStr);
                }

                // 요구 사항 파싱 (옵션)
                List<String> requirements = new ArrayList<>();
                if (requirementsIndex != -1 && row.length > requirementsIndex && !row[requirementsIndex].trim().isEmpty() && !row[requirementsIndex].matches("\\d+") ) {
                    String requirementsField = row[requirementsIndex].replace("\"", "").trim();
                    requirements = Arrays.asList(requirementsField.split(",\\s*"));
                } else {
                    // 요구 사항이 비어있다면 빈 리스트로 설정
                    requirements = new ArrayList<>();
                }

                // Task 객체 생성 및 리스트에 추가
                Task task = new Task(name, employees, difficulty, requirements);
                tasks.add(task);

            } catch (Exception e) {
                System.err.println("Error parsing row: " + Arrays.toString(row) + ". Error: " + e.getMessage());
            }
        }

        return tasks;
    }
}