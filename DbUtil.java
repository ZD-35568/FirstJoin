package pl.coderslab.mysql.javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    static final String DB_URL = "";
    static final String DB_USER = "";
    static final String DB_PASS = "";

    static String url = "jdbc:mysql://localhost:3306/mydatabase";
    static String user = "myusername";
    static String password = "mypassword";

    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {connection = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}


