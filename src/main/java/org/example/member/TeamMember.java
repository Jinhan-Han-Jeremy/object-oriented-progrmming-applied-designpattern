import java.util.List;

// 추상 멤버 클래스
public abstract class TeamMember {
    private String name;
    private String role;
    private List<String> skills;

    public TeamMember(String name, String role, List<String> skills) {
        this.name = name;
        this.role = role;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public List<String> getSkills() {
        return skills;
    }

    public abstract void performDuties();
}