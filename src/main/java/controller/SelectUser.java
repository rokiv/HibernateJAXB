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

@WebServlet("/select-user")
public class SelectUser extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        logger.info("Searching for user...");

        RequestDispatcher dispatcher = request.getServletContext().
                getRequestDispatcher("/WEB-INF/views/select-user.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        resp.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        User user = null;
        try (Connection con = Utils.getDBConnection("sa", "sa")) {
            user = DBUtils.findUser(con, id);
            logger.info("User with ID:" + id + " is displayed");
        } catch (SQLException e) {
            logger.error("SQLError: " + e);
        }
        req.setAttribute("user", user);
        req.setAttribute("title", "Selected user:");
        String address = "/WEB-INF/views/show-user.jsp";
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}