package org.example.performance;

import java.util.List;

public interface PerformanceCalculator {
    int LevelAssigner();
    int MeanNumber(List<Integer> numbers);
    void StandardDeviationMaker(int mean);

}
