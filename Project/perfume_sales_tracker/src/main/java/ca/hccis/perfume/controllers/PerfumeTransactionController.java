package ca.hccis.perfume.controllers;

import ca.hccis.perfume.jpa.entity.CodeValue;
import ca.hccis.perfume.repositories.CodeValueRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/perfumetransaction")
public class PerfumeTransactionController {

    private final PerfumeTransactionRepository _ptr;
    private final CodeValueRepository _cvr; // Inject the new repo

    private static final Logger logger = LoggerFactory.getLogger(PerfumeTransactionController.class);

    @Autowired
    public PerfumeTransactionController(PerfumeTransactionRepository ptr, CodeValueRepository cvr) {
        _ptr = ptr;
        _cvr = cvr;
    }

    // Define the ID we used in SQL
    private static final int PERFUME_TYPE_ID = 100;

    // Helper method to load the dropdown list
    private void loadPerfumeDropdown(Model model) {
        // Use the magic method we made in Step 3
        List<CodeValue> brandList = _cvr.findByCodeTypeId(PERFUME_TYPE_ID);
        model.addAttribute("perfumeBrands", brandList);
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

        // --- CRITICAL CHECK 2: Load the list for the dropdown on search results ---
        loadPerfumeDropdown(model);


        return "perfumetransaction/list";
    }

    /*
    * @author: Fardin
    * @since: 20251124
    * Goal: Adding a dropdown list to the view page so that user can select perfume brand and get the sorted result
    *
    * */

    @RequestMapping(value = {"/", "/perfumetransaction"})
    public String list(Model model) {
        // ... (Your existing code to fetch all transactions) ...
        Iterable<PerfumeTransaction> transactions = _ptr.findAll();
        model.addAttribute("perfumeTransactions", transactions);

        // --- ADD THE DROPDOWN LIST FOR SEARCH ---
        loadPerfumeDropdown(model);
        // ---------------------------------------

        model.addAttribute("perfumeTransaction", new PerfumeTransaction());
        return "perfumetransaction/list";
    }

    /**
     * Delete a transaction
     */

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            _ptr.deleteById(id);

            // Use RedirectAttributes to pass the message across the redirect
            redirectAttributes.addFlashAttribute("messageSuccess", "Transaction ID " + id + " was successfully deleted.");

        } catch (Exception e) {
            // Use RedirectAttributes for the error message
            redirectAttributes.addFlashAttribute("messageError", "Error deleting Transaction ID " + id + ". It may not exist.");
        }

        // CRITICAL: Use "redirect:" to force the browser to make a new GET request to the list page
        return "redirect:/perfumetransaction";
    }

    /**
     * Page to add new entity
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {

        loadPerfumeDropdown(model); // <--- Call helper

        PerfumeTransaction perfumeTransaction = new PerfumeTransaction();
        model.addAttribute("perfumeTransaction", perfumeTransaction);
        return "perfumetransaction/add";
    }

    /**
     * Page to edit an existing entity
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

        loadPerfumeDropdown(model); // <--- Call helper

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
    // Inside ca.hccis.perfume.controllers.PerfumeTransactionController
    @RequestMapping("/submit")
    public String submit(Model model, @Valid @ModelAttribute("perfumeTransaction") PerfumeTransaction perfumeTransaction,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // Line near the top of your submit method:
        boolean isNew = (perfumeTransaction.getId() == null || perfumeTransaction.getId().intValue() == 0);

        // Use this variable in your logic:
        if (isNew) {
            // ...
            redirectAttributes.addFlashAttribute("messageSuccess", "New Transaction added successfully!");
        } else {
            // ...
            redirectAttributes.addFlashAttribute("messageSuccess", "Transaction ID " + perfumeTransaction.getId() + " updated successfully!");
        }

        // ... validation and calculation logic here ...
        // --- BUSINESS LOGIC: CALCULATE PRICE ---
        try {
            double quantity = perfumeTransaction.getQuantity();
            double pricePerBottle = perfumeTransaction.getPricePerBottle();

            // Calculate Sub Total
            double sub = quantity * pricePerBottle;

            // Calculate Tax (e.g., 10%)
            final double TAX_RATE = 0.10;
            double tax = sub * TAX_RATE;

            // Calculate Total
            double total = sub + tax;

            // Set the calculated values back into the object
            perfumeTransaction.setSubTotal(sub);
            perfumeTransaction.setTaxAmount(tax);
            perfumeTransaction.setTotal(total);

        } catch (NullPointerException | ArithmeticException e) {
            // This catch block handles cases where the quantity or price was somehow null
            // (though @NotNull should catch it, this is a safety measure).
            System.err.println("Error during calculation: " + e.getMessage());
            model.addAttribute("messageError", "A fatal calculation error occurred. Check price and quantity values.");

            loadPerfumeDropdown(model); // loading the list
            return "perfumetransaction/add";
        }

        if (bindingResult.hasErrors()) {

            System.out.println("Validation error detected.");

            // --- CRITICAL FIX START ---
            // You MUST reload the dropdown list here.
            // If you don't, Thymeleaf crashes because ${perfumeBrands} is null.
            loadPerfumeDropdown(model);
            // --- CRITICAL FIX END ---
            // Validation fails, stay on the form page, no redirect needed
            return "perfumetransaction/add";
        }


        // Save the object (JPA handles Insert or Update based on ID)
        _ptr.save(perfumeTransaction);


        // Check if an ID exists before saving
        //boolean isNew = perfumeTransaction.getId() == null || perfumeTransaction.getId() == 0;

        return "redirect:/perfumetransaction";
    }

    /**
     * Search for a customer name
     */
    // Inside ca.hccis.perfume.controllers.PerfumeTransactionController

    /**
     * Search for records by Customer Name OR Perfume Choice.
     */
    @RequestMapping("/search")
    public String search(Model model, @ModelAttribute("perfumeTransaction") PerfumeTransaction perfumeTransaction) {

        String customerName = perfumeTransaction.getCustomerName();
        String perfumeChoice = perfumeTransaction.getPerfumeChoice();

        List<PerfumeTransaction> searchResults;

        if (!customerName.isEmpty() && !perfumeChoice.isEmpty()) {
            //Option 1: Search by both customer name and perfume choice
            searchResults = _ptr.findByCustomerNameContainingAndPerfumeChoiceContaining(customerName, perfumeChoice);
            model.addAttribute("messageSuccess", "Searching by Name and Perfume Choice.");

        } else if (!customerName.isEmpty()) {
            // Option 2: Search by Customer Name only
            searchResults = _ptr.findByCustomerNameContaining(customerName);
            model.addAttribute("messageSuccess", "Searching by Name Only.");


        } else if (!perfumeChoice.isEmpty()) {
            // Option 3: Search by Perfume Choice only
            searchResults = _ptr.findByPerfumeChoiceContaining(perfumeChoice);
            model.addAttribute("messageSuccess", "Searching by Perfume Choice Only.");


        } else {
            // Option 4: No fields entered, show all records (or show none)
            searchResults = _ptr.findAll();
            model.addAttribute("messageError", "Please enter a search term.");
        }

        model.addAttribute("perfumeTransactions", searchResults);
        logger.debug("searched for name:" + customerName + " and perfume: " + perfumeChoice);
// --- CRITICAL CHECK 2: Load the list for the dropdown on search results ---
        loadPerfumeDropdown(model);
        return "perfumetransaction/list";
    }
}