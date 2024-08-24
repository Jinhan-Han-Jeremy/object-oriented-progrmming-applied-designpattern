package org.github.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileInput {

    String filePath = "your_csv_file.csv"; // CSV 파일 경로
    String targetColumn = "프로젝트 목표와 범위 설정"; // 특정 칼럼 이름
    int targetNumber = 42; // 찾고자 하는 특정 숫자
/**
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] columns = line.split(",");
            // 여기서 columns[0], columns[1], ... 등으로 각 칼럼에 접근 가능
            // 예시: columns[0]은 첫 번째 칼럼의 값

            // 특정 칼럼에서 특정 숫자를 찾는 로직 추가
            // 예시: if (columns[1].equals(targetColumn) && Integer.parseInt(columns[2]) == targetNumber) {
            //         해당 행의 데이터를 가져옴
            //     }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
 **/
}
