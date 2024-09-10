package org.github.file;

import org.github.member.TeamMember;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamMemberParser implements CSVParser<TeamMember> {

    @Override
    public List<TeamMember> parse(List<String[]> data) {
        List<TeamMember> teamMembers = new ArrayList<>();

        if (data == null || data.isEmpty()) {
            throw new CSVFormatException("No data available to parse.");
        }

        for (int i = 1; i < data.size(); i++) {
            String[] row = data.get(i);
            if (row.length < 4) {
                throw new CSVFormatException("Invalid data format at row: " + i);
            }

            String name = row[0];
            String role = row[1];
            int level;
            try {
                level = Integer.parseInt(row[2]);
            } catch (NumberFormatException e) {
                throw new CSVFormatException("Invalid number format for 'level' at row: " + i);
            }

            List<String> skills = new ArrayList<>();
            Map<String, Integer> evaluations = parseEvaluations(row, data.get(0));

            //need to fix
            boolean state = Boolean.parseBoolean(row[3]);

            TeamMember member = new TeamMember(name, role, level, state, skills, evaluations);
            teamMembers.add(member);
        }

        return teamMembers;
    }

    private Map<String, Integer> parseEvaluations(String[] row, String[] headers) {
        Map<String, Integer> evaluations = new HashMap<>();
        for (int j = 4; j < row.length; j++) {
            String evalType = headers[j];

            if (!row[j].isEmpty()) {
                try {
                    evaluations.put(evalType, Integer.parseInt(row[j]));
                } catch (NumberFormatException e) {
                    throw new CSVFormatException("Invalid number format for evaluation at column: " + j);
                }
            }
        }
        return evaluations;
    }
}