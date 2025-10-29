package ca.hccis.perfume.bo;

import ca.hccis.perfume.dao.BusPassDAO;
import ca.hccis.perfume.jpa.entity.BusPass;
import ca.hccis.perfume.jpa.entity.CodeValue;
import ca.hccis.perfume.repositories.CodeValueRepository;
import ca.hccis.perfume.util.CisUtility;
import ca.hccis.perfume.util.CisUtilityFile;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class BusPassBO {

    public static ArrayList<BusPass> processDateRangeReport(String start, String end) {

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        BusPassDAO busPassDAO = new BusPassDAO();
        ArrayList<BusPass> busPasses = busPassDAO.selectAllByDateRange(start, end);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("dateRangeReport", busPasses);

        return busPasses;
    }

    public static ArrayList<BusPass> processMinLengthReport(int minLength) throws SQLException {

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        BusPassDAO busPassDAO = new BusPassDAO();
        ArrayList<BusPass> busPasses = null;

        try {
            busPasses = busPassDAO.selectAllWithMinLength(minLength);
        } catch (SQLException e) {
            throw e;
        }

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("minLengthReport", busPasses);

        return busPasses;
    }

    /**
     * Calculate the cost of the bus pass and set it's value in the bus pass object.
     *
     *         $1 per day for the first 20 days
     *         $0.50 for each day over 20 days
     *
     *         $10 premium if to include rural routes
     *
     *         Adjustments based on type
     *         3-K12 are free
     *         4-Seniors get a 25% discount on their subtotal
     *         5-Students get a 20% discount on their subtotal
     *
     * @param busPass
     * @return the cost
     * @author BJM
     * @since 20241025
     */
    public static double calculateBusPassCost(BusPass busPass) {

        double cost = 1 * busPass.getLengthOfPass();

        //adjust cost since days over 20 are 0.5$ less per day
        if (busPass.getLengthOfPass() > 20) {
            cost -= (busPass.getLengthOfPass() - 20) * 0.5;
        }

        if (busPass.getValidForRuralRoute()) {
            cost += 10;
        }

        switch (busPass.getPassType()) {
            case 3: //regular
                break;
            case 4: //k12
                cost = 0;
                break;
            case 5: //student 20% discount
                cost *= (1 - 0.2);
                break;
            case 6: //senior 25% discount
                cost *= (1 - 0.25);
                break;
        }

        busPass.setCost(new BigDecimal(cost));
        return cost;

    }

    /**
     * Set default values
     *
     * @param busPass
     * @author BJM
     * @since 20241025
     */
    public static void setBusPassDefaults(BusPass busPass) {
        busPass.setLengthOfPass(0);
        busPass.setCost(new BigDecimal(0));
        busPass.setPassType(3);
        busPass.setStartDate(CisUtility.getCurrentDate("yyyy-MM-dd"));

    }

    public static void setBusPassTypes(CodeValueRepository _cvr, HttpSession session) {
        List<CodeValue> busPassTypes = (List) session.getAttribute("busPassTypes");
        if (busPassTypes == null) {
            busPassTypes = _cvr.findByCodeTypeId(2);
            session.setAttribute("busPassTypes", busPassTypes);
        }


    }

}
