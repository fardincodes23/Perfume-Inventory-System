package info.hccis.bus.pass.controllers;

import info.hccis.bus.pass.bo.BusPassBO;
import info.hccis.bus.pass.entity.ReportPerfume;
import info.hccis.bus.pass.jpa.entity.BusPass;
import info.hccis.bus.pass.util.CisUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller to administer reports of the project.
 *
 * @author BJM
 * @since 20220616
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    /**
     * Send the user to list of reports view.
     *
     * @param model
     * @param session
     * @return To the appropriate view
     * @author BJM
     * @since 20220624
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        //BJM 20200602 Issue#1 Set the current date in the session
        logger.info("Running the reports controller base method");
        return "report/list";
    }


    /**
     * Method to send user to the Perfume name report.
     *
     * @param model
     * @return view for list
     * @author Fardin
     * @since 2025-10-28
     */
    @RequestMapping("/perfume/name")
    public String reportPerfumeNme(Model model, HttpSession session) {
        logger.info("Running the reports controller perfume name method");
        return "report/reportPerfumeName";
    }


    /**
     * Method to send user to the date range report.
     *
     * @param model
     * @return view for list
     * @author BJM
     * @since 2024-10-10
     */
    @RequestMapping("/buspass/daterange")
    public String reportBusPassDateRange(Model model, HttpSession session) {

        //Ensure that the current date is set in the session
        //BJM 20200602 Issue#1 Set the current date in the session
        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        String start = CisUtility.getCurrentDate(-30, "yyyy-MM-dd");
        String end = CisUtility.getCurrentDate(+30, "yyyy-MM-dd");

        //**********************************************************************
        // Put the report object in the model and send the user
        // to the report input view.
        //**********************************************************************
        ReportPerfume reportPerfume = new ReportPerfume();
        //Set the default start/end dates for the report
        reportPerfume.setDateStart(start);
        reportPerfume.setDateEnd(end);

        model.addAttribute("reportInput", reportPerfume);

        return "report/reportBusPassDateRange";
    }

    /**
     * Process the report
     *
     * @param model
     * @param reportPerfume Object containing inputs for the report
     * @return view to show report
     * @author BJM
     * @since 2024-10-10
     */
    @RequestMapping("/buspass/daterange/submit")
    public String reportBusPassDateRangeSubmit(Model model, @ModelAttribute("reportInput") ReportPerfume reportPerfume) {

        //Call BO method to process the report
        ArrayList<BusPass> busPasses = BusPassBO.processDateRangeReport(reportPerfume.getDateStart(), reportPerfume.getDateEnd());

        //Put the list in the Java object
        reportPerfume.setBusPasses(busPasses);

        //Add a message in case the report does not contain any data
        if (busPasses != null && busPasses.isEmpty()) {
            model.addAttribute("message", "No data found");
            System.out.println("BJM - no data found");
        }

        //Put object in model so it can be used on the view (html)
        model.addAttribute("reportInput", reportPerfume);

        return "report/reportBusPassDateRange";
    }

    /**
     * Method to send user to the min length report.
     *
     * @param model
     * @return view for list
     * @author BJM
     * @since 2024-10-10
     */
    @RequestMapping("/buspass/minlength")
    public String reportBusPassMinLength(Model model) {

        //**********************************************************************
        // Put the report object in the model and send the user
        // to the report input view.
        //**********************************************************************
        model.addAttribute("reportInput", new ReportPerfume());

        return "report/reportBusPassMinLength";
    }

    /**
     * Process the report
     *
     * @param model
     * @param reportPerfume Object containing inputs for the report
     * @return view to show report
     * @author BJM
     * @since 2024-10-11
     */
    @RequestMapping("/buspass/minlength/submit")
    public String reportBusPassMinLengthSubmit(Model model, @ModelAttribute("reportInput") ReportPerfume reportPerfume) {

        ArrayList<BusPass> busPasses;
        try {
            busPasses = BusPassBO.processMinLengthReport(reportPerfume.getMinLength());
        } catch (SQLException e) {
            model.addAttribute("message", "Exception accessing database");
            busPasses = null;
        }

        reportPerfume.setBusPasses(busPasses);

        if (busPasses != null && busPasses.isEmpty()) {
            model.addAttribute("message", "No data found");
            System.out.println("BJM - no data found");
        }

        model.addAttribute("reportInput", reportPerfume);

        return "report/reportBusPassMinLength";
    }

}
