package init;

import model.Utils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.h2.jdbc.JdbcConnection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.SQLException;

public class Log4jInit extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        String logFileName = config.getInitParameter("logfile");

        Logger globallog = null;
        ServletContext sc = config.getServletContext();
        String pref = sc.getRealPath("/");

        PropertyConfigurator.configure(pref + logFileName);

        globallog = Logger.getRootLogger();

        sc.setAttribute("log4", globallog);
        sc.setAttribute("logfilename", logFileName);

        globallog.info("=========================");
        globallog.info("Load logger on start-up.");

        try (Connection con = Utils.getDBConnection("sa","sa");) {
            globallog.info("init db connection");
        } catch (SQLException x) {
            x.printStackTrace();
        }

    }
}
