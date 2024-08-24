package org.github.task;

import java.util.List;

public interface TaskInterface {
    String getName();
    int getPriorityNumber();
    List<String> getEmployees();
    int getDifficulty();
    boolean canBeHandledBy(String employeeRole);
}