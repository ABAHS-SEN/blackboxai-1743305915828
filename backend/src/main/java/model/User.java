package model;

import java.sql.Date;

public class User {
    private String username;
    private String name;
    private Date dob;
    private String university;
    private int likesCount;
    private int dislikesCount;
    private String sex;

    public User() {}

    public User(String username, String name, Date dob, String university, 
                int likesCount, int dislikesCount, String sex) {
        this.username = username;
        this.name = name;
        this.dob = dob;
        this.university = university;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
        this.sex = sex;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
    
    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university = university; }
    
    public int getLikesCount() { return likesCount; }
    public void setLikesCount(int likesCount) { this.likesCount = likesCount; }
    
    public int getDislikesCount() { return dislikesCount; }
    public void setDislikesCount(int dislikesCount) { this.dislikesCount = dislikesCount; }
    
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", university='" + university + '\'' +
                ", likesCount=" + likesCount +
                ", dislikesCount=" + dislikesCount +
                ", sex='" + sex + '\'' +
                '}';
    }
}