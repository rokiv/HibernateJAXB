package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static User findUser(Connection con, int id) throws SQLException {
        String sql = "SELECT id, first_name,last_name " +
                "FROM users_table " +
                "WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            User user = new User(first_name,last_name);
            user.setId(id);
            return user;
        }
        return null;
    }

    public static User insertUser(Connection con, String first_name, String last_name)
            throws SQLException {
        String sql = "INSERT INTO users_table(id,first_name, last_name) " +
                "VALUES(?,?,?)";
        String sqlSelect = "SELECT MAX(id) FROM users_table";
        PreparedStatement sstmt = con.prepareStatement(sqlSelect);
        ResultSet rs = sstmt.executeQuery();
        int maxId=0;
        if (rs.next()) {
            maxId = rs.getInt(1);
        }
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1,String.valueOf(++maxId));
        statement.setString(2,first_name);
        statement.setString(3,last_name);
        statement.executeUpdate();
        User user = new User(first_name,last_name);
        user.setId(maxId);
        return user;
    }

    public static void updateUser(Connection con, User user)
            throws SQLException{
        String sql = "UPDATE users_table SET first_name=?,last_name=? WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,user.getFirstName());
        pstmt.setString(2,user.getLastName());
        pstmt.setInt(3,user.getId());
        if (pstmt.executeUpdate()==0)
            throw new SQLException();
    }

    public static List<User> getAllUsers(Connection con) throws SQLException {
        String sql = "SELECT * FROM users_table";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User(rs.getString("first_name"),rs.getString("last_name"));
            user.setId(rs.getInt("id"));
            users.add(user);
        }
        return users;
    }


}
