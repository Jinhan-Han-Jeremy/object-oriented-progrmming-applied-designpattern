package org.github.utility;

import org.github.member.TeamMember;

import java.util.ArrayList;
import java.util.List;


import org.github.task.Task;

import java.util.HashMap;
import java.util.Map;

public class UtilityEquations implements UtilityEquationsInterface {
    private float standardDeviation;

    @Override
    public float calculateMean(float total, int numbers) {
        return total / numbers; // 단순히 총 시간 / 멤버 수로 가정
    }

    // 메인 메서드: 멤버 리스트와 업무 리스트를 사용하여 연립방정식을 해결
    @Override
    public List<Float> calculatePerformanceRates(List<TeamMember> members, List<Task> tasks, float totalDays) {
        List<Float> performanceList = new ArrayList<>();

        // 각 멤버의 성과율을 계산하여 리스트에 추가
        for (TeamMember member : members) {
            float memberPerformance = calculateMean(totalDays, members.size());
            performanceList.add(memberPerformance);
        }

        return performanceList;
    }


    private double[] gaussianElimination(double[][] a, double[] b) {
        int n = b.length;

        // 전진 소거 단계
        for (int i = 0; i < n; i++) {
            // 주 대각 성분이 0이 아닌지 확인하고, 그렇다면 행을 바꿉니다.
            int max = i;

            for (int j = i + 1; j < n; j++) {
                if (Math.abs(a[j][i]) > Math.abs(a[max][i])) {
                    max = j;
                }
            }

            double[] temp = a[i];
            a[i] = a[max];
            a[max] = temp;

            double t = b[i];
            b[i] = b[max];
            b[max] = t;

            // 0으로 만들기
            for (int j = i + 1; j < n; j++) {
                double factor = a[j][i] / a[i][i];
                b[j] -= factor * b[i];
                for (int k = i; k < n; k++) {
                    a[j][k] -= factor * a[i][k];
                }
            }
        }

        // 후진 대입 단계
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += a[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / a[i][i];
        }
        return x;
    }


    @Override
    public void StandardDeviationMaker(float mean) {
        // 표준 편차 계산 로직 (예시)
        double sumOfSquares = 0;
        List<Integer> data = new ArrayList<>();  // 실제 데이터를 전달 받아야 함
        for (int number : data) {
            sumOfSquares += Math.pow((number - mean), 2);
        }
        double variance = sumOfSquares / data.size();
        this.standardDeviation = (float) Math.sqrt(variance);
    }

    @Override
    public float getStandardDeviation() {
        return standardDeviation;
    }

    @Override
    public List<TeamMember> gridAlgorithm(List<TeamMember> members) {
        // 특정 그리드 알고리즘에 따라 멤버 리스트를 처리하는 로직
        return members;
    }

    @Override
    // 멤버 리스트와 업무 리스트를 사용하여 연립방정식을 해결하는 함수
    public AA multipleEquations(List<TeamMember> members, List<Task> tasks, float totalDays) {
        Map<String, Float> performanceMap = new HashMap<>();

        // 예를 들어, 첫 번째 케이스(존, 밥, 제이와 업무 b, d)가 총 3일 걸린다고 가정하고 연립방정식을 세웁니다.
        // 각 멤버의 성과율을 구해야 합니다.

        for (Task task : tasks) {
            float taskContribution = totalDays / tasks.size(); // 각 업무에 걸린 평균 시간
            for (TeamMember member : members) {
                String key = member.getName() + "-" + task.getName();
                float currentRate = member.performanceRate(task.getName());
                performanceMap.put(key, taskContribution / currentRate);
            }
        }

        return new AA(performanceMap); // 각 멤버와 업무에 따른 퍼포먼스율 반환
    }

    public static class AA{

        Map<String, Float> value;

        public AA(Map<String, Float> value) {
            this.value = value;
        }

        public Map<String, Float> getValue() {
            return value;
        }
    }
}

