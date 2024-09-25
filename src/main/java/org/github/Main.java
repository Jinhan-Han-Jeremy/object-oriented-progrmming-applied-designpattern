package org.github;

import org.github.file.CSVFileReadException;
import org.github.file.CSVFormatException;
import org.github.file.TeamMemberManager;
import org.github.member.TeamMember;

public class Main {
    public static void main(String[] args) {










        System.out.println("Starting Team Member Manager...");

        // Create an instance of TeamMemberManager using the singleton pattern
        TeamMemberManager manager = TeamMemberManager.INSTANCE;

        // Try to load team members from the CSV file
        try {
            manager.loadTeamMembers("C:\\Users\\USER\\IdeaProjects\\object-oriented-progrmming-applied-designpattern\\files\\team_member.csv");

            // Print all loaded team members
            System.out.println("startt ");
            for (TeamMember member : manager.getTeamMembers()) {
                System.out.println(member);
            }
        } catch (CSVFileReadException | CSVFormatException e) {
            // Handle exceptions by printing error messages to the console
            System.err.println("An error occurred while loading team members: " + e.getMessage());
            e.printStackTrace(); // Optionally, log stack trace for debugging purposes
        }

        System.out.println("Team Member Manager has finished execution.");
    }
}