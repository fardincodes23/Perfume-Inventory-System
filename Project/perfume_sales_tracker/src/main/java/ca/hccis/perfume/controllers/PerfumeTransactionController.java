package ca.hccis.perfume.controllers;

import ca.hccis.perfume.repositories.PerfumeTransactionRepository;
import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/perfumetransaction")
public class PerfumeTransactionController {

    private final PerfumeTransactionRepository _ptr;
    private static final Logger logger = LoggerFactory.getLogger(PerfumeTransactionController.class);

    @Autowired
    public PerfumeTransactionController(PerfumeTransactionRepository ptr) {
        _ptr = ptr;
    }

    /**
     * Home page - Lists all transactions
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        // Load all transactions from DB
        Iterable<PerfumeTransaction> transactions = _ptr.findAll();

        model.addAttribute("perfumeTransactions", transactions);
        // This blank object is needed for the Search form on the list page
        model.addAttribute("perfumeTransaction", new PerfumeTransaction());

        return "perfumetransaction/list";
    }

    /**
     * Delete a transaction
     */
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        try {
            _ptr.deleteById(id);
            model.addAttribute("messageSuccess", "Transaction deleted");
        } catch(Exception e){
            model.addAttribute("messageError", "Exception deleting transaction");
        }

        // Reload the list
        Iterable<PerfumeTransaction> transactions = _ptr.findAll();
        model.addAttribute("perfumeTransactions", transactions);
        model.addAttribute("perfumeTransaction", new PerfumeTransaction());
        return "perfumetransaction/list";
    }

    /**
     * Page to add new entity
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {
        PerfumeTransaction perfumeTransaction = new PerfumeTransaction();
        // You might want to set default date here if needed
        model.addAttribute("perfumeTransaction", perfumeTransaction);
        return "perfumetransaction/add";
    }

    /**
     * Page to edit an existing entity
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

        Optional<PerfumeTransaction> transaction = _ptr.findById(id);
        if (transaction.isPresent()) {
            model.addAttribute("perfumeTransaction", transaction.get());
            return "perfumetransaction/add"; // Re-uses the add page
        }

        model.addAttribute("messageError", "Could not load the transaction");
        return "redirect:/perfumetransaction";
    }

    /**
     * Submit method (Processes both Add and Edit)
     */
    @RequestMapping("/submit")
    public String submit(Model model, HttpServletRequest request,
                         @Valid @ModelAttribute("perfumeTransaction") PerfumeTransaction perfumeTransaction,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("Validation error");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            // Send them back to the add page to fix errors
            return "perfumetransaction/add";
        }

        // Calculate total if needed before saving (Optional business logic)
        // perfumeTransaction.setTotal(perfumeTransaction.getQuantity() * perfumeTransaction.getPricePerBottle());

        _ptr.save(perfumeTransaction);
        return "redirect:/perfumetransaction";
    }

    /**
     * Search for a customer name
     */
    @RequestMapping("/search")
    public String search(Model model, @ModelAttribute("perfumeTransaction") PerfumeTransaction perfumeTransaction) {

        // Use the repository method we added to find by customer name
        List<PerfumeTransaction> searchResults = _ptr.findByCustomerNameContaining(perfumeTransaction.getCustomerName());

        model.addAttribute("perfumeTransactions", searchResults);
        logger.debug("searched for name:" + perfumeTransaction.getCustomerName());

        return "perfumetransaction/list";
    }
}