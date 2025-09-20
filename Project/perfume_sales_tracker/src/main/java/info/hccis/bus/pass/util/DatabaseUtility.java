package info.hccis.bus.pass.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Utility methods for working with jdbc
 *
 * @author bjm
 * @since 20201007
 */
public class DatabaseUtility {

        public static boolean checkConnection() {

        Connection conn = null;
        try {
            conn = DatabaseUtility.getConnection("");
        } catch (Exception e) {
            return false;
        }

        return true;

    }
    
    /**
     * Get a connection to the database
     *
     * @param connectionProperties CSV with db name, username, and password
     * @return Connection to the specified db or default from application
     * properties file
     * @since 20201007
     * @author BJM
     */
    public static Connection getConnection(String connectionProperties) throws SQLException {
        System.out.println("BJM-getting connection from db utility class.");
        String connectionString, userName, password;
        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        connectionString = rb.getString("spring.datasource.url");
        if (connectionProperties.isEmpty()) {
            //get from application.properties file
            userName = rb.getString("spring.datasource.username");
            password = rb.getString("spring.datasource.password");
        } else {
            String connectionStringNew = connectionString.substring(0, connectionString.lastIndexOf("/"));
            String[] passedIn = connectionProperties.split(",");
            connectionString = connectionStringNew + "/" + passedIn[0];
            userName = passedIn[1];
            try {
                password = passedIn[2];
            } catch (Exception e) {
                password = "";
            }
        }
        Connection conn = null;

        try {
            //BJM 20200925 Removed the password.
            conn = DriverManager.getConnection(
                    connectionString,
                    userName,
                    password);
        } catch (SQLException ex) {
            System.out.println("SQL exception happned!");
            throw ex;
        }

        return conn;

    }

}
