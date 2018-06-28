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

@WebServlet("/update-user")
public class UpdateUser extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        logger.info("Updating info about user");

        RequestDispatcher dispatcher = request.getServletContext().
                getRequestDispatcher("/WEB-INF/views/update-user.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        logger.info("Enter to update user servlet");
        resp.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        User user;
        try (Connection con = Utils.getDBConnection("sa","sa")) {
            user = new User(firstName,lastName);
            user.setId(id);
            DBUtils.updateUser(con,user);
            logger.info("User with ID: " + id + " is updated.");
            req.setAttribute("user", user);
        } catch (SQLException e) {
            logger.error(e + ". There's no user with such id");
        }
        req.setAttribute("title","Updated user:");
        String address = "/WEB-INF/views/show-user.jsp";
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}
