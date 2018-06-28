package jaxb;

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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/unmarshall")
public class JaxbUnmarshaller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        logger.info("Unmarshalling users to XML file...");

        File file = new File("C:/java/users.xml");
/*
        try (Connection con = Utils.getDBConnection("sa","sa")){
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Users usersWrapper = (Users)unmarshaller.unmarshal(file);
            List<User> users = usersWrapper.getList();
            for (User user: users
                 ) {

                try {
                    DBUtils.insertFromXml(con,user);
                } catch (Exception e) {
                    logger.warn("User ID=" + user.getId() + " is already exists.");
                }

            }
            logger.info("Users were unmarshalling from XML file successfully.");

        } catch (JAXBException e) {
            logger.error(e);
            e.printStackTrace();
        } catch (SQLException x) {
            logger.error(x);
            x.printStackTrace();
        }*/
        req.setAttribute("title", "All users were unmarshalling from XML file");
        String address = "/WEB-INF/views/jaxb-results.jsp";
        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher(address);
        requestDispatcher.forward(req,resp);

    }
}
