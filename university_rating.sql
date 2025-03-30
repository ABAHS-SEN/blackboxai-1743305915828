-- Database creation
CREATE DATABASE IF NOT EXISTS university_rating;
USE university_rating;

-- University table
CREATE TABLE University (
    university_name VARCHAR(255) PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    student_count INT NOT NULL,
    admin_user VARCHAR(255)
);

-- Users table
CREATE TABLE Users (
    username VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dob DATE,
    university VARCHAR(255),
    likes_count INT DEFAULT 0,
    dislikes_count INT DEFAULT 0,
    sex ENUM('M', 'F') NOT NULL,
    FOREIGN KEY (university) REFERENCES University(university_name)
);

-- Update University table foreign key
ALTER TABLE University
ADD CONSTRAINT fk_admin_user
FOREIGN KEY (admin_user) REFERENCES Users(username);

-- User_Auth table
CREATE TABLE User_Auth (
    username VARCHAR(255) PRIMARY KEY,
    password TEXT NOT NULL,
    security_question VARCHAR(255),
    answer VARCHAR(255),
    FOREIGN KEY (username) REFERENCES Users(username)
);

-- User_Images table
CREATE TABLE User_Images (
    username VARCHAR(255) PRIMARY KEY,
    image TEXT,
    FOREIGN KEY (username) REFERENCES Users(username)
);

-- Transactions table
CREATE TABLE Transactions (
    user1 VARCHAR(255),
    user2 VARCHAR(255),
    impact ENUM('upvote', 'downvote') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user1, user2, created_at),
    FOREIGN KEY (user1) REFERENCES Users(username),
    FOREIGN KEY (user2) REFERENCES Users(username)
);

-- User_Logs table
CREATE TABLE User_Logs (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    log_text TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (username) REFERENCES Users(username)
);

-- Sample data insertion
INSERT INTO University (university_name, location, student_count)
VALUES 
    ('Tech University', 'New York', 15000),
    ('State College', 'California', 22000),
    ('Global Institute', 'London', 18000);

INSERT INTO Users (username, name, dob, university, sex)
VALUES
    ('admin1', 'Admin User', '1980-01-01', 'Tech University', 'M'),
    ('user1', 'John Doe', '1995-05-15', 'State College', 'M'),
    ('user2', 'Jane Smith', '1998-08-20', 'Global Institute', 'F');

UPDATE University SET admin_user = 'admin1' WHERE university_name = 'Tech University';

INSERT INTO User_Auth (username, password)
VALUES
    ('admin1', 'admin123'),
    ('user1', 'password1'),
    ('user2', 'password2');

INSERT INTO User_Images (username, image)
VALUES
    ('admin1', 'admin1.jpg'),
    ('user1', 'user1.jpg'),
    ('user2', 'user2.jpg');