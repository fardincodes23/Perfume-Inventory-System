package info.hccis.bus.pass.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import info.hccis.bus.pass.jpa.entity.BusPass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO class to access ticket orders.
 *
 * @author bjmaclean
 * @since 20220621
 */
public class BusPassDAO {

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(BusPassDAO.class);

    public BusPassDAO() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }

    }

    /**
     * Select all
     *
     * @since 20210924
     * @author BJM
     */
    public ArrayList<BusPass> selectAll() {
        ArrayList<BusPass> passes = null;
        Statement stmt = null;

        //******************************************************************
        //Use the DriverManager to get a connection to our MySql database.  Note
        //that in the dependencies, we added the Java connector to MySql which 
        //will allow us to connect to a MySql database.
        //******************************************************************
        //******************************************************************
        //Create a statement object using our connection to the database.  This 
        //statement object will allow us to run sql commands against the database.
        //******************************************************************
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from BusPass;");

            //******************************************************************
            //Loop through the result set using the next method.  
            //******************************************************************
            passes = new ArrayList();

            while (rs.next()) {

                BusPass busPass = new BusPass();
                busPass.setId(rs.getInt(1));
                busPass.setName(rs.getString("name"));
                busPass.setAddress(rs.getString("address"));
                busPass.setCity(rs.getString("city"));
                busPass.setLengthOfPass(rs.getInt("lengthOfPass"));

                passes.add(busPass);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return passes;
    }

    /**
     * Select all by date range
     *
     * @since 20241010
     * @author BJM
     */
    public ArrayList<BusPass> selectAllByDateRange(String start, String end) {
        ArrayList<BusPass> passes = null;
        Statement stmt = null;

        //******************************************************************
        //Use the DriverManager to get a connection to our MySql database.  Note
        //that in the dependencies, we added the Java connector to MySql which
        //will allow us to connect to a MySql database.
        //******************************************************************
        //******************************************************************
        //Create a statement object using our connection to the database.  This
        //statement object will allow us to run sql commands against the database.
        //******************************************************************
        try {

            stmt = conn.createStatement();
            String sqlStatement = "select * from BusPass " +
                    "where startDate >= '"+start
                    +"' and startDate <= '"+end+"';";

            rs = stmt.executeQuery(sqlStatement);

            //******************************************************************
            //Loop through the result set using the next method.
            //******************************************************************
            passes = new ArrayList();

            while (rs.next()) {

                BusPass busPass = new BusPass();
                busPass.setId(rs.getInt(1));
                busPass.setName(rs.getString("name"));
                busPass.setAddress(rs.getString("address"));
                busPass.setCity(rs.getString("city"));
                busPass.setLengthOfPass(rs.getInt("lengthOfPass"));

                passes.add(busPass);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return passes;
    }

    /**
     * Select all for min length report
     *
     * @since 20241011
     * @author BJM
     */
    public ArrayList<BusPass> selectAllWithMinLength(int minLength) throws SQLException {
        ArrayList<BusPass> passes = null;
        Statement stmt = null;

        //******************************************************************
        //Use the DriverManager to get a connection to our MySql database.  Note
        //that in the dependencies, we added the Java connector to MySql which
        //will allow us to connect to a MySql database.
        //******************************************************************
        //******************************************************************
        //Create a statement object using our connection to the database.  This
        //statement object will allow us to run sql commands against the database.
        //******************************************************************
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from BusPass " +
                    "where lengthOfPass >= "+minLength+";");

            //******************************************************************
            //Loop through the result set using the next method.
            //******************************************************************
            passes = new ArrayList();

            while (rs.next()) {

                BusPass busPass = new BusPass();
                busPass.setId(rs.getInt(1));
                busPass.setName(rs.getString("name"));
                busPass.setAddress(rs.getString("address"));
                busPass.setCity(rs.getString("city"));
                busPass.setLengthOfPass(rs.getInt("lengthOfPass"));

                passes.add(busPass);
            }

        } catch (SQLException e) {

            e.printStackTrace();
            throw e;

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return passes;
    }
}
