# University Rating System - Setup Guide

## Prerequisites
1. Java JDK 11+
2. Apache Maven 3.6+
3. MySQL Server 8.0+
4. Node.js 16+
5. Apache Tomcat 9.0+ (for Jakarta EE)

## Database Setup
1. Start MySQL server
2. Create the database and tables:
```bash
mysql -u root -p < university_rating.sql
```

## Backend Setup
1. Navigate to the backend directory:
```bash
cd backend
```
2. Build the WAR file:
```bash
mvn clean package
```
3. Deploy to Tomcat:
   - Copy `target/university-rating.war` to Tomcat's `webapps` directory
   - Start Tomcat server

## Frontend Setup
1. Navigate to the frontend directory:
```bash
cd frontend
```
2. Install dependencies:
```bash
npm install
```
3. Start the development server:
```bash
npm start
```

## Application URLs
- Backend: http://localhost:8080/university-rating/
- Frontend: http://localhost:3000/

## Configuration
1. Database connection:
   - Edit `backend/src/main/java/util/DatabaseConnection.java`
   - Update URL, username, and password as needed

2. CORS Configuration:
   - The UsersServlet already includes CORS headers for development
   - For production, configure proper CORS in your reverse proxy or application server
   - Example CORS headers in UsersServlet:
```java
response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
response.setHeader("Access-Control-Allow-Methods", "GET");
response.setHeader("Access-Control-Allow-Headers", "Content-Type");
```

## Testing
1. Register a new user through the frontend
2. Rate another user
3. Verify data in MySQL:
```sql
SELECT * FROM Users;
SELECT * FROM Transactions;
SELECT * FROM User_Logs;
```

## Production Considerations
1. Implement password hashing
2. Add CSRF protection
3. Configure HTTPS
4. Set up proper logging
5. Implement input validation
6. Add user authentication