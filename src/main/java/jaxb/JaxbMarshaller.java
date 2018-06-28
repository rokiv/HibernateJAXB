package jaxb;

import model.DBUtils;
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
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/marshall")
public class JaxbMarshaller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        logger.info("Marshalling users to XML file...");

        /*try (Connection con = Utils.getDBConnection("sa","sa")){
            Users users = new Users();
            users.setList(DBUtils.selectAllWithAddress(con));
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(users, new File("C:/java/users.xml"));
            logger.info("Users were marshalled successfully.");
        } catch (SQLException e) {
            logger.error(e);
        } catch (JAXBException e) {
            logger.error(e);
        }*/
        req.setAttribute("title", "All users were marshalling to XML file");
        String address = "/WEB-INF/views/jaxb-results.jsp";
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}
