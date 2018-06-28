package controller;

import model.DBUtils;
import model.User;
import model.Utils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/add-user")
public class AddUser extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        logger.info("Enter to add user servlet");

        RequestDispatcher dispatcher = request.getServletContext().
                getRequestDispatcher("/WEB-INF/views/add-user.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        logger.info("Adding new user to DB");

        resp.setContentType("text/html;charset=utf-8");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        User user = null;
        try (Connection con = Utils.getDBConnection("sa","sa")) {
            user = DBUtils.insertUser(con,firstName,lastName);
            logger.info("User with ID:"+user.getId()+ " added successfully");
        } catch (SQLException e) {
            logger.error("SQLError: " + e);
        }
        req.setAttribute("user", user);
        req.setAttribute("title","Added user:");
        String address = "/WEB-INF/views/show-user.jsp";
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}
