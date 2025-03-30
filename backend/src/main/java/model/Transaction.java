package model;

import java.sql.Timestamp;

public class Transaction {
    private String user1;
    private String user2;
    private String impact;
    private Timestamp createdAt;

    public Transaction() {}

    public Transaction(String user1, String user2, String impact, Timestamp createdAt) {
        this.user1 = user1;
        this.user2 = user2;
        this.impact = impact;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getUser1() { return user1; }
    public void setUser1(String user1) { this.user1 = user1; }

    public String getUser2() { return user2; }
    public void setUser2(String user2) { this.user2 = user2; }

    public String getImpact() { return impact; }
    public void setImpact(String impact) { this.impact = impact; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Transaction{" +
                "user1='" + user1 + '\'' +
                ", user2='" + user2 + '\'' +
                ", impact='" + impact + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}