package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Create a new user
    public boolean createUser(User user) {
        String sql = "INSERT INTO Users (username, name, dob, university, likes_count, dislikes_count, sex) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getName());
            statement.setDate(3, user.getDob());
            statement.setString(4, user.getUniversity());
            statement.setInt(5, user.getLikesCount());
            statement.setInt(6, user.getDislikesCount());
            statement.setString(7, user.getSex());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get user by username
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        User user = null;
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                user = new User(
                    resultSet.getString("username"),
                    resultSet.getString("name"),
                    resultSet.getDate("dob"),
                    resultSet.getString("university"),
                    resultSet.getInt("likes_count"),
                    resultSet.getInt("dislikes_count"),
                    resultSet.getString("sex")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Update user's like/dislike counts
    public boolean updateUserRatings(String username, boolean isLike) {
        String column = isLike ? "likes_count" : "dislikes_count";
        String sql = "UPDATE Users SET " + column + " = " + column + " + 1 WHERE username = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                User user = new User(
                    resultSet.getString("username"),
                    resultSet.getString("name"),
                    resultSet.getDate("dob"),
                    resultSet.getString("university"),
                    resultSet.getInt("likes_count"),
                    resultSet.getInt("dislikes_count"),
                    resultSet.getString("sex")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}