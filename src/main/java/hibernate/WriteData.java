package hibernate;

import jaxb.Users;
import model.Address;
import model.User;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet("/output")
public class WriteData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = (Logger) getServletContext().getAttribute("log4");
        logger.info("Write users to XML file...");

        UserService userService = new UserService();

        /*List<User> users = userService.getAllUsers();
        User user1 = userService.findUser(2);
        User user2 = new User("andrei","andreev");
        Address addr2 = new Address();
        addr2.setCity("ekb");
        addr2.setStreet("lunacharskogo");
        addr2.setBuilding("5");
        user2.addAddress(addr2);
        userService.saveUser(user2);
        users = userService.getAllUsers();*/

        try {
            List<User> users = userService.getAllUsers();
            Users usersWrapper = new Users();
            usersWrapper.setList(users);
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(usersWrapper, new File("C:/java/users.xml"));
            logger.info("Users were marshalled successfully.");
        } catch (JAXBException e) {
            logger.error(e);
            e.printStackTrace();
        }


        req.setAttribute("title", "All users were written to XML file");
        String address = "/WEB-INF/views/results.jsp";
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(address);
        dispatcher.forward(req, resp);
    }
}
