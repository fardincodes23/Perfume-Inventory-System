package ca.hccis.perfume.controllers;

import ca.hccis.perfume.repositories.PerfumeTransactionRepository; // The JPA Repository
import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/perfumetransaction") // Base URL for all actions: /perfumetransaction/...
public class PerfumeTransactionController {

    // Inject the JPA Repository (DAO replacement)
    @Autowired
    private PerfumeTransactionRepository perfumeTransactionRepository;

    // ----------------------------------------------------------------------
    // C - CREATE: Method to show the blank Add Form
    // Maps to: http://localhost:8080/perfumetransaction/add
    // ----------------------------------------------------------------------
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // 1. Give the form a blank object to bind to
        model.addAttribute("perfumeTransaction", new PerfumeTransaction());
        // 2. Return the new add template
        return "perfumetransaction/add";
    }

    // ----------------------------------------------------------------------
    // C - CREATE: Method to handle the POST submission (Saves the record)
    // ----------------------------------------------------------------------
    @PostMapping("/save")
    public String saveTransaction(@Valid @ModelAttribute("perfumeTransaction") PerfumeTransaction perfumeTransaction,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // If validation fails, return to the form with error messages
            return "perfumetransaction/add";
        }

        // Use the JPA Repository's save method
        perfumeTransactionRepository.save(perfumeTransaction);

        // Redirect to the list view to show the saved record
        return "redirect:/perfumetransaction/list";
    }

    // ----------------------------------------------------------------------
    // R - READ: Method to show the list of all transactions
    // Maps to: http://localhost:8080/perfumetransaction/list
    // ----------------------------------------------------------------------
    @GetMapping("/list")
    public String listAllTransactions(Model model) {
        // Use JPA Repository's findAll() to get all records
        model.addAttribute("transactions", perfumeTransactionRepository.findAll());
        return "perfumetransaction/list";
    }

    // ----------------------------------------------------------------------
    // D - DELETE: Method to delete a record
    // Maps to: /perfumetransaction/delete?id=5
    // ----------------------------------------------------------------------
    @GetMapping("/delete")
    public String deleteTransaction(@RequestParam int id) {
        perfumeTransactionRepository.deleteById(id);
        // Redirect back to the list
        return "redirect:/perfumetransaction/list";
    }
}