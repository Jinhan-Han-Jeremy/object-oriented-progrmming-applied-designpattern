package org.github.file;
import org.github.task.Task;

import java.util.ArrayList;
import java.util.List;

public enum TaskManager {
    INSTANCE;

    private List<Task> tasks;

    // Constructor for initializing the tasks list
    TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Method to load tasks from a CSV file
    public void loadTasks(String filePath) {
        try {
            // Load the CSV data from the specified file
            List<String[]> data = FileInput.getInstance().loadCSV(filePath);

            // Use the TaskParser to parse the CSV data
            TaskParser parser = new TaskParser();
            this.tasks = parser.parse(data);

            System.out.println("Tasks loaded successfully.");
        } catch (Exception e) {
            // Print an error message if any exceptions occur
            System.err.println("An error occurred while loading tasks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to return a copy of the tasks list
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    // Main method for testing
    public static void main(String[] args) {
        TaskManager manager = TaskManager.INSTANCE;

        // Load the tasks from the specified CSV file path
        manager.loadTasks("C:\\Users\\USER\\IdeaProjects\\object-oriented-progrmming-applied-designpattern\\files\\assigned_tasks.csv");

        // Print the loaded tasks
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
    }
}