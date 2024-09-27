package org.github.file;

import org.github.member.TeamMember;

import java.util.ArrayList;
import java.util.List;

public enum TeamMemberManager {

    INSTANCE;

    private List<TeamMember> teamMembers;

    // Constructor for initializing the teamMembers list
    TeamMemberManager() {
        this.teamMembers = new ArrayList<>();
    }

    // Method to load team members from a CSV file
    public void loadTeamMembers(String filePath) {
        try {
            List<String[]> data = FileInput.getInstance().loadCSV(filePath);
            TeamMemberParser parser = new TeamMemberParser();
            this.teamMembers = parser.parse(data);
        } catch (CSVFileReadException | CSVFormatException e) {
            System.err.println("An error occurred while loading team members: " + e.getMessage());
            e.printStackTrace(); // Optionally, log stack trace for debugging
        }
    }

    // Method to return a copy of the team members list
    public List<TeamMember> getTeamMembers() {
        return new ArrayList<>(teamMembers); // Defensive copy to avoid external modification
    }

    // Example main method for testing purposes
    public static void main(String[] args) {
        TeamMemberManager manager = TeamMemberManager.INSTANCE;
        manager.loadTeamMembers("C:\\Users\\USER\\IdeaProjects\\object-oriented-progrmming-applied-designpattern\\files\\team_member.csv");

        for (TeamMember member : manager.getTeamMembers()) {
            System.out.println(member);
        }
    }
}