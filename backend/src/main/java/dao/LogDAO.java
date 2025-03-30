package dao;

import model.UserLogs;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    private Connection connection;

    public LogDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Create a new log entry
    public boolean createLog(UserLogs log) {
        String sql = "INSERT INTO User_Logs (username, log_text) VALUES (?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, log.getUsername());
            statement.setString(2, log.getLogText());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all logs for a user
    public List<UserLogs> getLogsByUser(String username) {
        List<UserLogs> logs = new ArrayList<>();
        String sql = "SELECT * FROM User_Logs WHERE username = ? ORDER BY created_at DESC";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                UserLogs log = new UserLogs(
                    resultSet.getInt("log_id"),
                    resultSet.getString("username"),
                    resultSet.getString("log_text"),
                    resultSet.getTimestamp("created_at")
                );
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    // Get all logs (admin view)
    public List<UserLogs> getAllLogs() {
        List<UserLogs> logs = new ArrayList<>();
        String sql = "SELECT * FROM User_Logs ORDER BY created_at DESC";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                UserLogs log = new UserLogs(
                    resultSet.getInt("log_id"),
                    resultSet.getString("username"),
                    resultSet.getString("log_text"),
                    resultSet.getTimestamp("created_at")
                );
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
}