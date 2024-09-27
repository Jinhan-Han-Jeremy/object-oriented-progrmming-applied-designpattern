package org.github.task;

import java.util.List;

public interface TaskInterface {
    String getName();
    List<String> getEmployees();
    int getDifficulty();
    List<String> getRequirements();
}