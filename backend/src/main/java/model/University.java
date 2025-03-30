package model;

public class University {
    private String universityName;
    private String location;
    private int studentCount;
    private String adminUser;

    public University() {}

    public University(String universityName, String location, int studentCount, String adminUser) {
        this.universityName = universityName;
        this.location = location;
        this.studentCount = studentCount;
        this.adminUser = adminUser;
    }

    // Getters and Setters
    public String getUniversityName() { return universityName; }
    public void setUniversityName(String universityName) { this.universityName = universityName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getStudentCount() { return studentCount; }
    public void setStudentCount(int studentCount) { this.studentCount = studentCount; }

    public String getAdminUser() { return adminUser; }
    public void setAdminUser(String adminUser) { this.adminUser = adminUser; }

    @Override
    public String toString() {
        return "University{" +
                "universityName='" + universityName + '\'' +
                ", location='" + location + '\'' +
                ", studentCount=" + studentCount +
                ", adminUser='" + adminUser + '\'' +
                '}';
    }
}