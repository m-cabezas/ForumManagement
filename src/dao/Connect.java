package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private static Connection conn;

    public static Connection getInstance() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:database/sqlite.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }


}