package org.github.utility;

import org.github.member.TeamMember;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;


import org.github.task.Task;

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


    public static BigDecimal calculateParallelTimeFromInverseSum(List<Double> times) {
        BigDecimal inverseSum = calculateInverseSum(times);
        if (inverseSum.compareTo(BigDecimal.ZERO) > 0) {
            return BigDecimal.ONE.divide(inverseSum, 10, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }
    // 8. 병렬 작업 시간을 계산하는 함수

    public static BigDecimal calculateParallelTime(List<Double> times, int memberCount) {
        BigDecimal inverseSum = calculateInverseSum(times);
        return BigDecimal.valueOf(memberCount).divide(inverseSum, MathContext.DECIMAL128);
    }

    public static BigDecimal calculateInverseSum(List<Double> times) {
        BigDecimal sum = BigDecimal.ZERO;
        for (double time : times) {
            if (time > 0 && Double.isFinite(time)) {
                BigDecimal timeBD = BigDecimal.valueOf(time);
                sum = sum.add(BigDecimal.ONE.divide(timeBD, 5, RoundingMode.HALF_UP));
            }
        }
        return sum;
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


}

