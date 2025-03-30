package dao;

import model.University;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniversityDAO {
    private Connection connection;

    public UniversityDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Get all universities
    public List<University> getAllUniversities() {
        List<University> universities = new ArrayList<>();
        String sql = "SELECT * FROM University";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                University university = new University(
                    resultSet.getString("university_name"),
                    resultSet.getString("location"),
                    resultSet.getInt("student_count"),
                    resultSet.getString("admin_user")
                );
                universities.add(university);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return universities;
    }

    // Get university by name
    public University getUniversityByName(String name) {
        String sql = "SELECT * FROM University WHERE university_name = ?";
        University university = null;
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                university = new University(
                    resultSet.getString("university_name"),
                    resultSet.getString("location"),
                    resultSet.getInt("student_count"),
                    resultSet.getString("admin_user")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return university;
    }

    // Update university admin
    public boolean updateUniversityAdmin(String universityName, String adminUsername) {
        String sql = "UPDATE University SET admin_user = ? WHERE university_name = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, adminUsername);
            statement.setString(2, universityName);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}