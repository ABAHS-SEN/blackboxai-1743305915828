package servlet;

import dao.RatingDAO;
import dao.UserDAO;
import dao.LogDAO;
import model.Transaction;
import model.UserLogs;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/rate")
public class RateServlet extends HttpServlet {
    private RatingDAO ratingDAO;
    private UserDAO userDAO;
    private LogDAO logDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        ratingDAO = new RatingDAO();
        userDAO = new UserDAO();
        logDAO = new LogDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user1 = request.getParameter("user1");
        String user2 = request.getParameter("user2");
        String impact = request.getParameter("impact");

        // Check if user1 has already rated user2
        if (ratingDAO.hasRated(user1, user2)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("You have already rated this user");
            return;
        }

        // Create transaction
        Transaction transaction = new Transaction(user1, user2, impact, null);
        boolean transactionCreated = ratingDAO.createRating(transaction);

        // Update user's rating count
        boolean ratingUpdated = userDAO.updateUserRatings(user2, "upvote".equals(impact));

        if (transactionCreated && ratingUpdated) {
            // Log the rating action
            String logText = String.format("User %s %s user %s", user1, impact, user2);
            logDAO.createLog(new UserLogs(0, user1, logText, null));
            
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("Rating submitted successfully");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Rating submission failed");
        }
    }
}