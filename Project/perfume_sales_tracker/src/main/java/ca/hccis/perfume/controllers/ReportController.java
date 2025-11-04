package ca.hccis.perfume.controllers;

import ca.hccis.perfume.dao.PerfumeTransactionDAO;
import ca.hccis.perfume.entity.ReportPerfume;
import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
     * Method to send user to the Perfume choice report.
     *
     * @param model
     * @return view for list
     * @author Fardin
     * @since 2025-10-28
     */
    @RequestMapping("/perfume/choice")
    public String reportPerfumeChoice(Model model, HttpSession session) {
        logger.info("Running the reports controller perfume name method");
        model.addAttribute("reportInput", new ReportPerfume());
        return "report/reportPerfumeChoice";
    }


    /**
     * Process the report
     *
     * @param model
     * @param reportPerfume Object containing inputs for the report
     * @return view to show report
     * @author Fardin
     * @since 2025-10-28
     */
    @RequestMapping("/perfume/choice/submit")
    public String reportPerfumeChoiceSubmit(Model model, @ModelAttribute("reportInput") ReportPerfume reportPerfume) {

        System.out.println("Name from input form: " + reportPerfume.getPerfumeChoice());


        //Write some model code to go to the db and get the appropriate reports
        //Add them to a collection in the ReportPerfume class

        PerfumeTransactionDAO perfumeDAO = new PerfumeTransactionDAO();
        ArrayList<PerfumeTransaction> theList = perfumeDAO.selectAllByPerfumeChoice(reportPerfume.getPerfumeChoice());
        reportPerfume.setPerfumeTransaction(theList);


        //Put object in model so it can be used on the view (html)
        if (theList == null || theList.isEmpty()) {
            model.addAttribute("message", "No records found for that name");
            System.out.println("Fardin- no data found");

        }
        model.addAttribute("reportInput", reportPerfume);


        return "report/reportPerfumeChoice"; //send user to another view
    }


//Remove below later


}






