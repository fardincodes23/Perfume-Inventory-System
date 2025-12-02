package ca.hccis.perfume.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import ca.hccis.perfume.jpa.entity.CodeValue;
import ca.hccis.perfume.jpa.entity.PerfumeTransactionList;
import ca.hccis.perfume.repositories.CodeValueRepository;
import ca.hccis.perfume.repositories.PerfumeTransactionRepository;
import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Main controller for the Perfume Sales Tracker application.
 * Handles HTTP requests for viewing, adding, updating, and deleting perfume sales,
 * and coordinates between the web layer and the data access layer.
 *
 * @author Fardin
 * @since 2025-10-11
 */


@Controller
@RequestMapping("/perfumetransaction")
public class PerfumeTransactionController {

    private final PerfumeTransactionRepository _ptr;
    private final CodeValueRepository _cvr;
    private static final Logger logger = LoggerFactory.getLogger(PerfumeTransactionController.class);

    @Autowired
    public PerfumeTransactionController(PerfumeTransactionRepository ptr, CodeValueRepository cvr) {
        _ptr = ptr;
        _cvr = cvr;
    }

    private static final int PERFUME_TYPE_ID = 100;
    private void loadPerfumeDropdown(Model model) {
        List<CodeValue> brandList = _cvr.findByCodeTypeId(PERFUME_TYPE_ID);
        model.addAttribute("perfumeBrands", brandList);
    }


    /**
     * Home page - Lists all transactions
     */
    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        Iterable<PerfumeTransaction> transactions = _ptr.findAll();
        model.addAttribute("perfumeTransactions", transactions);
        model.addAttribute("perfumeTransaction", new PerfumeTransaction());
        loadPerfumeDropdown(model);
        return "perfumetransaction/list";
    }

    /**
     * @author: Fardin
     * @since: 20251124
     * Goal: Adding a dropdown list to the view page so that user can select perfume brand
     * and get the sorted result
     *
     */

    @RequestMapping(value = {"/", "/perfumetransaction"})
    public String list(Model model) {
        Iterable<PerfumeTransaction> transactions = _ptr.findAll();
        model.addAttribute("perfumeTransactions", transactions);
        loadPerfumeDropdown(model);
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
            redirectAttributes.addFlashAttribute("messageSuccess", "Transaction ID " + id + " was successfully deleted.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageError", "Error deleting Transaction ID " + id + ". It may not exist.");
        }
        return "redirect:/perfumetransaction";
    }

    /**
     * Page to add new entity
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {

        loadPerfumeDropdown(model);

        PerfumeTransaction perfumeTransaction = new PerfumeTransaction();
        model.addAttribute("perfumeTransaction", perfumeTransaction);
        return "perfumetransaction/add";
    }

    /**
     * Page to edit an existing entity
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

        loadPerfumeDropdown(model);

        Optional<PerfumeTransaction> transaction = _ptr.findById(id);
        if (transaction.isPresent()) {
            model.addAttribute("perfumeTransaction", transaction.get());
            return "perfumetransaction/add";
        }

        model.addAttribute("messageError", "Could not load the transaction");
        return "redirect:/perfumetransaction";
    }

    /**
     * Submit method (Processes both Add and Edit)
     */


    @RequestMapping("/submit")
    public String submit(Model model, @Valid @ModelAttribute("perfumeTransaction") PerfumeTransaction perfumeTransaction,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        boolean isNew = (perfumeTransaction.getId() == null || perfumeTransaction.getId().intValue() == 0);

        if (isNew) {
            redirectAttributes.addFlashAttribute("messageSuccess", "New Transaction added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("messageSuccess", "Transaction ID " + perfumeTransaction.getId() + " updated successfully!");
        }

        try {
            ca.hccis.perfume.bo.PerfumeBo.calculate(perfumeTransaction);

        } catch (NullPointerException | ArithmeticException e) {
            model.addAttribute("messageError", "Calculation error.");
            loadPerfumeDropdown(model);
            return "perfumetransaction/add";
        }

        if (perfumeTransaction.getQuantity() > 50 && perfumeTransaction.getPricePerBottle() > 100.00) {
            bindingResult.rejectValue("pricePerBottle", "error.perfumeTransaction",
                    "Bulk Order Policy: For quantities over 50, price must be $100.00 or less.");
        }
        try {

            if (perfumeTransaction.getTransactionDate() != null && !perfumeTransaction.getTransactionDate().isEmpty()) {
                LocalDate inputDate = LocalDate.parse(perfumeTransaction.getTransactionDate());

                if (inputDate.isAfter(LocalDate.now())) {
                    bindingResult.rejectValue("transactionDate", "error.perfumeTransaction",
                            "Transaction Date cannot be in the future.");
                }
            }
        } catch (DateTimeParseException e) {
            System.err.println("Date parse error: " + e.getMessage());
        }


        if (bindingResult.hasErrors()) {
            System.out.println("Validation error detected.");
            loadPerfumeDropdown(model);
            return "perfumetransaction/add";
        }
        _ptr.save(perfumeTransaction);
        return "redirect:/perfumetransaction";
    }

    /**
     * Search for records by Customer Name OR Perfume Choice.
     */

    @RequestMapping("/search")
    public String search(Model model, @ModelAttribute("perfumeTransaction") PerfumeTransaction perfumeTransaction) {

        String customerName = perfumeTransaction.getCustomerName();
        String perfumeChoice = perfumeTransaction.getPerfumeChoice();

        List<PerfumeTransaction> searchResults;

        if (!customerName.isEmpty() && !perfumeChoice.isEmpty()) {
            searchResults = _ptr.findByCustomerNameContainingAndPerfumeChoiceContaining(customerName, perfumeChoice);
            model.addAttribute("messageSuccess", "Searching by Name and Perfume Choice.");

        } else if (!customerName.isEmpty()) {
            searchResults = _ptr.findByCustomerNameContaining(customerName);
            model.addAttribute("messageSuccess", "Searching by Name Only.");


        } else if (!perfumeChoice.isEmpty()) {
            searchResults = _ptr.findByPerfumeChoiceContaining(perfumeChoice);
            model.addAttribute("messageSuccess", "Searching by Perfume Choice Only.");


        } else {
            searchResults = _ptr.findAll();
            model.addAttribute("messageError", "Please enter a search term.");
        }

        model.addAttribute("perfumeTransactions", searchResults);
        logger.debug("searched for name:" + customerName + " and perfume: " + perfumeChoice);
        loadPerfumeDropdown(model);
        return "perfumetransaction/list";
    }

    @RequestMapping("/listedit")
    public String listEdit(Model model) {
        List<PerfumeTransaction> allTransactions = (List<PerfumeTransaction>) _ptr.findAll();
        PerfumeTransactionList wrapper = new PerfumeTransactionList();
        wrapper.setTransactions(allTransactions);
        model.addAttribute("perfumeTransactionList", wrapper);
        loadPerfumeDropdown(model);

        return "perfumetransaction/listedit";
    }


    @PostMapping("/listedit/submit")
    public String listEditSubmit(
            @ModelAttribute("perfumeTransactionList") PerfumeTransactionList transactionsList,
            RedirectAttributes redirectAttributes) {

        List<PerfumeTransaction> formList = transactionsList.getTransactions();
        if (formList == null) {
            return "redirect:/perfumetransaction";
        }

        List<PerfumeTransaction> recordsToSave = new ArrayList<>();
        int updateCount = 0;

        for (PerfumeTransaction formTransaction : formList) {

            PerfumeTransaction dbRecord = _ptr.findById(formTransaction.getId()).orElse(null);

            if (dbRecord != null) {
                dbRecord.setCustomerName(formTransaction.getCustomerName());
                dbRecord.setPhoneNumber(formTransaction.getPhoneNumber());
                dbRecord.setQuantity(formTransaction.getQuantity());
                dbRecord.setPricePerBottle(formTransaction.getPricePerBottle());

                if (dbRecord.getQuantity() != null && dbRecord.getPricePerBottle() != null) {
                    double quantity = dbRecord.getQuantity();
                    double price = dbRecord.getPricePerBottle();
                    double sub = quantity * price;
                    final double TAX_RATE = 0.10;
                    double tax = sub * TAX_RATE;
                    double total = sub + tax;

                    dbRecord.setSubTotal(sub);
                    dbRecord.setTaxAmount(tax);
                    dbRecord.setTotal(total);
                }

                recordsToSave.add(dbRecord);
                updateCount++;
            }
        }

        try {
            _ptr.saveAll(recordsToSave);
            redirectAttributes.addFlashAttribute("messageSuccess", "Successfully updated " + updateCount + " records!");
        } catch (Exception e) {
            System.err.println("Batch Save Error: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("messageError", "Batch update failed due to a database/mapping error.");
        }

        return "redirect:/perfumetransaction";
    }

}