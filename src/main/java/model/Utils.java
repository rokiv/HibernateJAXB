package model;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class Utils {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=runscript from '~/create-h.sql'";
    private static JdbcConnectionPool cp;

    public static Connection getDBConnection(String user, String password) {
        Connection dbConnection = null;
        if (cp == null) {
            cp = JdbcConnectionPool.create(DB_CONNECTION, user, password);
        }
        try {
            dbConnection = cp.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

}