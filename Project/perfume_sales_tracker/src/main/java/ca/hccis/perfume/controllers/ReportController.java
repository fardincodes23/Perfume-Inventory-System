package ca.hccis.perfume.controllers;

import ca.hccis.perfume.bo.PerfumeTransactionBO;
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
 * @author Fardin
 * @since 2025-10-11
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
     * @author Fardin
     * @since 2025-10-11
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {
        logger.info("Running the reports controller base method");
        return "report/list";
    }

    /**
     * Method to send user to the Perfume choice report.
     *
     * @param model
     * @return view for list
     * @author Fardin
     * @since 2025-10-14
     */
    @RequestMapping("/perfume/choice")
    public String reportPerfumeChoice(Model model) {
        logger.info("Running the reports controller perfume name method");
        model.addAttribute("reportInput", new ReportPerfume());
        return "report/reportPerfumeChoice";
    }

    /**
     * Method to send user to the Perfume Sale Transaction to view All Records.
     *
     * @param model
     * @return view for perfume/list
     * @author Fardin
     * @since 2025-10-11
     */

    @RequestMapping("/records")
    public String listAllTransactions(Model model) {
        logger.info("Running the reports controller to list all transactions");

        PerfumeTransactionDAO transactionDAO = new PerfumeTransactionDAO();
        ArrayList<PerfumeTransaction> allTransactions = transactionDAO.selectAll();
        model.addAttribute("records", allTransactions);
        if (allTransactions == null || allTransactions.isEmpty()) {
            model.addAttribute("message", "No perfume transactions found in the database.");
        }
        return "perfume/list";
    }


    /**
     * Process the report
     *
     * @param model
     * @param reportPerfume Object containing inputs for the report
     * @return view to show report
     * @author Fardin
     * @since 2025-10-11
     */
    @RequestMapping("/perfume/choice/submit")
    public String reportPerfumeChoiceSubmit(Model model, @ModelAttribute("reportInput") ReportPerfume reportPerfume) {

        System.out.println("Name from input form: " + reportPerfume.getPerfumeChoice());

        PerfumeTransactionBO perfumeTransactionBO = new PerfumeTransactionBO();
        ArrayList<PerfumeTransaction> theList = perfumeTransactionBO.processSelectAllByPerfumeChoice(reportPerfume.getPerfumeChoice());
        reportPerfume.setPerfumeTransaction(theList);
        if (theList == null || theList.isEmpty()) {
            model.addAttribute("message", "No records found for that name");
            System.out.println("Fardin- no data found");

        }
        model.addAttribute("reportInput", reportPerfume);


        return "report/reportPerfumeChoice";
    }


}






