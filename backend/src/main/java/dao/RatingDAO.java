package dao;

import model.Transaction;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO {
    private Connection connection;

    public RatingDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Create a new rating transaction
    public boolean createRating(Transaction transaction) {
        String sql = "INSERT INTO Transactions (user1, user2, impact) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, transaction.getUser1());
            statement.setString(2, transaction.getUser2());
            statement.setString(3, transaction.getImpact());
            
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if user has already rated another user
    public boolean hasRated(String user1, String user2) {
        String sql = "SELECT 1 FROM Transactions WHERE user1 = ? AND user2 = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user1);
            statement.setString(2, user2);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all transactions for a user
    public List<Transaction> getTransactionsByUser(String username) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions WHERE user1 = ? OR user2 = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                    resultSet.getString("user1"),
                    resultSet.getString("user2"),
                    resultSet.getString("impact"),
                    resultSet.getTimestamp("created_at")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}