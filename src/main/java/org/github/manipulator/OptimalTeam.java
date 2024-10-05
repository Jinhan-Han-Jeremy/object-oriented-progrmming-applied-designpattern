package org.github.manipulator;

import org.github.member.TeamMember;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import  org.github.utility.UtilityEquations;

public class OptimalTeam implements OptimalTeamInterface {

    public OptimalTeam() {
    }

    @Override
    public List<TeamMember> findOptimalTeamForTask(List<TeamMember> teamMembers, double[][] timeMatrix, int taskIdx) {
        BigDecimal bestTime = new BigDecimal(Double.MAX_VALUE);
        List<TeamMember> bestCombination = new ArrayList<>();

        iterateCombinations(teamMembers, timeMatrix, taskIdx, bestTime, bestCombination);

        return bestCombination;
    }

    // 1. 모든 조합을 순회하는 함수
    private void iterateCombinations(List<TeamMember> teamMembers, double[][] timeMatrix, int taskIdx, BigDecimal bestTime, List<TeamMember> bestCombination) {
        for (int i = 0; i < teamMembers.size() - 2; i++) {
            for (int j = i + 1; j < teamMembers.size() - 1; j++) {
                for (int k = j + 1; k < teamMembers.size(); k++) {
                    processCombination(i, j, k, teamMembers, timeMatrix, taskIdx, bestTime, bestCombination);
                }
            }
        }
    }

    // 2. 각 조합에 대해 처리하는 함수
    private void processCombination(int i, int j, int k, List<TeamMember> teamMembers, double[][] timeMatrix, int taskIdx, BigDecimal bestTime, List<TeamMember> bestCombination) {
        double time1 = timeMatrix[i][taskIdx];
        double time2 = timeMatrix[j][taskIdx];
        double time3 = timeMatrix[k][taskIdx];

        long count = getNonFiniteCount(time1, time2, time3);

        updateBestCombination(count, i, j, k, time1, time2, time3, teamMembers, bestTime, bestCombination);
    }

    @Override
    public BigDecimal calculateTaskTimeByMembers(List<TeamMember> teamMembers, List<TeamMember> selectedTeam, double[][] timeMatrix, int taskIdx) {
        List<Double> times = new ArrayList<>();
        for (TeamMember member : selectedTeam) {
            for (int i = 0; i < teamMembers.size(); i++) {
                if (teamMembers.get(i).getName().equals(member.getName())) {
                    times.add(timeMatrix[i][taskIdx]);
                    break;
                }
            }
        }
        return UtilityEquations.calculateParallelTimeFromInverseSum(times);
    }

    // 3. 비유한 값의 개수를 계산하는 함수
    private long getNonFiniteCount(double... times) {
        long count = 0;
        for (Double time : times) {
            if (time != null && !Double.isFinite(time)) {
                count++;
            }
        }
        return count;
    }

    // 4. 최적의 팀 조합을 업데이트하는 함수
    private void updateBestCombination(long count, int i, int j, int k, double time1, double time2, double time3, List<TeamMember> teamMembers, BigDecimal bestTime, List<TeamMember> bestCombination) {
        if (count == 0) {
            processAllFiniteCase(i, j, k, time1, time2, time3, teamMembers, bestTime, bestCombination);
        } else if (count == 1) {
            processOneInfiniteCase(i, j, k, time1, time2, time3, teamMembers, bestTime, bestCombination);
        } else if (count == 2) {
            processTwoInfiniteCase(i, j, k, time1, time2, time3, teamMembers, bestTime, bestCombination);
        }
    }

    // 5. 모든 값이 유한한 경우 처리하는 함수
    private void processAllFiniteCase(int i, int j, int k, double time1, double time2, double time3, List<TeamMember> teamMembers, BigDecimal bestTime, List<TeamMember> bestCombination) {
        List<Double> times = Arrays.asList(time1, time2, time3);
        BigDecimal parallelTime = UtilityEquations.calculateParallelTime(times, 3);

        if (parallelTime.compareTo(bestTime) < 0) {
            bestTime = parallelTime;
            bestCombination.clear();
            bestCombination.addAll(Arrays.asList(teamMembers.get(i), teamMembers.get(j), teamMembers.get(k)));
        }
    }

    // 6. 하나의 값이 유한하지 않은 경우 처리하는 함수
    private void processOneInfiniteCase(int i, int j, int k, double time1, double time2, double time3, List<TeamMember> teamMembers, BigDecimal bestTime, List<TeamMember> bestCombination) {
        BigDecimal parallelTime;
        if (!Double.isFinite(time1)) {
            parallelTime = UtilityEquations.calculateParallelTime(Arrays.asList(time2, time3), 2);
            updateCombinationIfBetter(parallelTime, bestTime, bestCombination, Arrays.asList(teamMembers.get(j), teamMembers.get(k)));
        } else if (!Double.isFinite(time2)) {
            parallelTime = UtilityEquations.calculateParallelTime(Arrays.asList(time1, time3), 2);
            updateCombinationIfBetter(parallelTime, bestTime, bestCombination, Arrays.asList(teamMembers.get(i), teamMembers.get(k)));
        } else {
            parallelTime = UtilityEquations.calculateParallelTime(Arrays.asList(time1, time2), 2);
            updateCombinationIfBetter(parallelTime, bestTime, bestCombination, Arrays.asList(teamMembers.get(i), teamMembers.get(j)));
        }
    }

    // 7. 두 개의 값이 유한하지 않은 경우 처리하는 함수
    private void processTwoInfiniteCase(int i, int j, int k, double time1, double time2, double time3, List<TeamMember> teamMembers, BigDecimal bestTime, List<TeamMember> bestCombination) {
        BigDecimal taskTime;
        if (Double.isFinite(time1)) {
            taskTime = BigDecimal.valueOf(time1);
            updateCombinationIfBetter(taskTime, bestTime, bestCombination, Arrays.asList(teamMembers.get(i)));
        } else if (Double.isFinite(time2)) {
            taskTime = BigDecimal.valueOf(time2);
            updateCombinationIfBetter(taskTime, bestTime, bestCombination, Arrays.asList(teamMembers.get(j)));
        } else {
            taskTime = BigDecimal.valueOf(time3);
            updateCombinationIfBetter(taskTime, bestTime, bestCombination, Arrays.asList(teamMembers.get(k)));
        }
    }


    // 9. 최적의 조합을 업데이트하는 함수
    private void updateCombinationIfBetter(BigDecimal parallelTime, BigDecimal bestTime, List<TeamMember> bestCombination, List<TeamMember> newCombination) {
        if (parallelTime.compareTo(bestTime) < 0) {
            bestTime = parallelTime;
            bestCombination.clear();
            bestCombination.addAll(newCombination);
        }
    }

    private List<String> ConvertedNamestFromTeam(List<TeamMember> team) {
        // 가변 리스트로 초기화
        List<String> names = new ArrayList<>();

        for (int i = 0; i < team.size(); i++) {
            names.add(team.get(i).getName());
        }

        return names;
    }

    @Override
    public void findOptimalTeamCombination(List<TeamMember> teamMembers, double[][] timeMatrix, List<String> tasks, int firstTaskIdx) {

        List<TeamMember> allMembers = new ArrayList<>(teamMembers);

        for (int i = 0; i < 3; i++) {
            int taskIdx = (firstTaskIdx + i) % tasks.size();
            List<TeamMember> taskTeam = findOptimalTeamForTask(allMembers, timeMatrix, taskIdx);
            BigDecimal taskTime = calculateTaskTimeByMembers(allMembers, taskTeam, timeMatrix, taskIdx);

            System.out.println((i == 0 ? "First" : i == 1 ? "Second" : "Third")
                    + " task " + tasks.get(taskIdx) + ": "
                    + String.join(", ", ConvertedNamestFromTeam(taskTeam))
                    + ", Completion time: " + taskTime + " days");

            allMembers = excludeTeamMembers(allMembers, taskTeam);
        }

    }

    @Override
    public List<TeamMember> excludeTeamMembers(List<TeamMember> allMembers, List<TeamMember> selectedMembers) {
        List<TeamMember> remainings = new ArrayList<>();
        for (TeamMember member : allMembers) {
            if (!selectedMembers.contains(member)) {
                remainings.add(member);
            }
        }
        return remainings;
    }
}