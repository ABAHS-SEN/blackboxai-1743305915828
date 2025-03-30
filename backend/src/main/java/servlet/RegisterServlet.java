package servlet;

import dao.UserAuthDAO;
import dao.UserDAO;
import dao.LogDAO;
import model.User;
import model.UserAuth;
import model.UserLogs;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;
    private UserAuthDAO userAuthDAO;
    private LogDAO logDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
        userAuthDAO = new UserAuthDAO();
        logDAO = new LogDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String university = request.getParameter("university");
        String sex = request.getParameter("sex");
        String password = request.getParameter("password");

        // Create user
        User user = new User(username, name, dob, university, 0, 0, sex);
        boolean userCreated = userDAO.createUser(user);

        // Create auth record
        UserAuth userAuth = new UserAuth(username, password, null, null);
        boolean authCreated = userAuthDAO.createUserAuth(userAuth);

        if (userCreated && authCreated) {
            // Log the registration
            logDAO.createLog(new UserLogs(0, username, "User registered successfully", null));
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.getWriter().write("User registered successfully");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Registration failed");
        }
    }
}