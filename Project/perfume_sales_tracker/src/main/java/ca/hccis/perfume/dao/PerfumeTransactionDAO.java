package ca.hccis.perfume.dao;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * DAO class to access perfume sales data.
 *
 * @author Fardin
 * @since 20251024
 */
public class PerfumeTransactionDAO {

    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(PerfumeTransactionDAO.class);

    public PerfumeTransactionDAO() {

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
     * Select all perfume transactions
     *
     * @since 20251024
     */
    public ArrayList<PerfumeTransaction> selectAll() {
        ArrayList<PerfumeTransaction> records = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;


        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM PerfumeTransaction;");

            records = new ArrayList<>();

            while (rs.next()) {
                PerfumeTransaction record = new PerfumeTransaction();
                record.setId(rs.getInt("id"));
                record.setTransactionDate(rs.getString("transactionDate"));
                record.setCustomerName(rs.getString("customerName"));
                record.setPhoneNumber(rs.getString("phoneNumber"));
                record.setPerfumeChoice(rs.getString("perfumeChoice"));
                record.setPerfumeSize(rs.getString("perfumeSize"));
                record.setPricePerBottle(rs.getDouble("pricePerBottle"));
                record.setQuantity(rs.getInt("quantity"));
                record.setSubTotal(rs.getDouble("subTotal"));
                record.setTaxAmount(rs.getDouble("taxAmount"));
                record.setTotalPrice(rs.getDouble("totalPrice"));


                records.add(record);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error closing statement");
            }
        }
        return records;
    }

    /**
     * Select perfume transactions by perfume choice
     *
     * @since 20251024
     */
    public ArrayList<PerfumeTransaction> selectAllByPerfumeChoice(String perfumeChoice) {
        ArrayList<PerfumeTransaction> records = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;


        try {
            stmt = conn.createStatement();
            String sqlStatement = "SELECT * FROM PerfumeTransaction " +
                    "WHERE perfumeChoice = '" + perfumeChoice + "'" +
                    "ORDER BY id DESC;";

            rs = stmt.executeQuery(sqlStatement);

            while (rs.next()) {
                PerfumeTransaction record = new PerfumeTransaction();
                record.setId(rs.getInt("id"));
                record.setTransactionDate(rs.getString("transactionDate"));
                record.setCustomerName(rs.getString("customerName"));
                record.setPhoneNumber(rs.getString("phoneNumber"));
                record.setPerfumeChoice(rs.getString("perfumeChoice"));
                record.setPerfumeSize(rs.getString("perfumeSize"));
                record.setPricePerBottle(rs.getDouble("pricePerBottle"));
                record.setQuantity(rs.getInt("quantity"));
                record.setSubTotal(rs.getDouble("subTotal"));
                record.setTaxAmount(rs.getDouble("taxAmount"));
                record.setTotalPrice(rs.getDouble("totalPrice"));

//                checking if DAO is
//
//
//                returning data
                System.out.println("Loaded record for: " + rs.getString("perfumeChoice"));

                records.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error closing statement");
            }
        }
        System.out.println("Query completed. Records found: " + records.size());

        return records;
    }
}
