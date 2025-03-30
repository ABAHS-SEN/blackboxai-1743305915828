package dao;

import model.UserAuth;
import util.DatabaseConnection;

import java.sql.*;

public class UserAuthDAO {
    private Connection connection;

    public UserAuthDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Create user authentication record
    public boolean createUserAuth(UserAuth userAuth) {
        String sql = "INSERT INTO User_Auth (username, password, security_question, answer) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userAuth.getUsername());
            statement.setString(2, userAuth.getPassword());
            statement.setString(3, userAuth.getSecurityQuestion());
            statement.setString(4, userAuth.getAnswer());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get user authentication by username
    public UserAuth getUserAuthByUsername(String username) {
        String sql = "SELECT * FROM User_Auth WHERE username = ?";
        UserAuth userAuth = null;
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                userAuth = new UserAuth(
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("security_question"),
                    resultSet.getString("answer")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAuth;
    }

    // Validate user credentials
    public boolean validateCredentials(String username, String password) {
        String sql = "SELECT 1 FROM User_Auth WHERE username = ? AND password = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}