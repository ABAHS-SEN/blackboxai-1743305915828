package model;

import java.sql.Timestamp;

public class UserLogs {
    private int logId;
    private String username;
    private String logText;
    private Timestamp createdAt;

    public UserLogs() {}

    public UserLogs(int logId, String username, String logText, Timestamp createdAt) {
        this.logId = logId;
        this.username = username;
        this.logText = logText;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getLogText() { return logText; }
    public void setLogText(String logText) { this.logText = logText; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "UserLogs{" +
                "logId=" + logId +
                ", username='" + username + '\'' +
                ", logText='" + logText + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}