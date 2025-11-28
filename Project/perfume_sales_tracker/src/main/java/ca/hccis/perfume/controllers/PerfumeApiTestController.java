package ca.hccis.perfume.controllers;

import ca.hccis.perfume.client.FragranceDto;
import ca.hccis.perfume.client.PerfumeApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for testing the external Fragella Perfume API
 * Handles all API test and search functionality
 *
 * JAVA 8 COMPATIBLE VERSION
 *
 * @author Your Name
 * @since 2025-11-28
 */
@Controller
@RequestMapping("/perfumeapi")
public class PerfumeApiTestController {

    private static final Logger logger = LoggerFactory.getLogger(PerfumeApiTestController.class);

    private final PerfumeApiClient perfumeApiClient;

    @Autowired
    public PerfumeApiTestController(PerfumeApiClient perfumeApiClient) {
        this.perfumeApiClient = perfumeApiClient;
    }

    /**
     * Display the Test API page with search form
     * GET /perfumeapi/test
     */
    @RequestMapping("/test")
    public String testApi(Model model) {
        model.addAttribute("searched", false);
        model.addAttribute("fragrances", new ArrayList<FragranceDto>());
        return "perfumetransaction/testapi";
    }

    /**
     * Handle search request from the Test API page
     * Calls the external Fragella API and displays results
     * POST /perfumeapi/test/search
     *
     * @param searchTerm The fragrance name to search for
     * @param model Spring Model to pass data to view
     * @return View name (testapi.html)
     */
    @PostMapping("/test/search")
    public String testApiSearch(Model model,
                                @RequestParam("searchTerm") String searchTerm) {
        try {
            // Input validation - Java 8 compatible
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                model.addAttribute("messageError", "Please enter a search term");
                model.addAttribute("searched", false);
                model.addAttribute("fragrances", new ArrayList<FragranceDto>());
                return "perfumetransaction/testapi";
            }

            logger.info("Searching fragrances for: " + searchTerm);

            // Call the external API
            List<FragranceDto> results = perfumeApiClient.searchFragrances(searchTerm, 10);

            // Check results
            if (results == null || results.isEmpty()) {
                model.addAttribute("messageError",
                        "No fragrances found for: " + searchTerm +
                                ". Try another search term.");
                model.addAttribute("fragrances", new ArrayList<FragranceDto>());
                model.addAttribute("searched", true);
                logger.warn("No results found for search: " + searchTerm);
            } else {
                model.addAttribute("messageSuccess",
                        "Found " + results.size() + " fragrance(s) matching: " + searchTerm);
                model.addAttribute("fragrances", results);
                model.addAttribute("searched", true);
                logger.info("Found " + results.size() + " fragrances");
            }

        } catch (Exception e) {
            logger.error("Error searching fragrances: " + e.getMessage(), e);
            model.addAttribute("messageError",
                    "Error calling the API: " + e.getMessage());
            model.addAttribute("fragrances", new ArrayList<FragranceDto>());
            model.addAttribute("searched", true);
        }

        return "perfumetransaction/testapi";
    }

    /**
     * Get popular fragrances (no search term)
     * Optional endpoint for future enhancement
     */
    @RequestMapping("/popular")
    public String getPopularFragrances(Model model) {
        try {
            List<FragranceDto> popular = perfumeApiClient.getPopularFragrances(10);
            model.addAttribute("fragrances", popular);
            model.addAttribute("messageSuccess", "Showing popular fragrances");
            model.addAttribute("searched", true);
        } catch (Exception e) {
            logger.error("Error fetching popular fragrances: " + e.getMessage(), e);
            model.addAttribute("messageError", "Error fetching popular fragrances: " + e.getMessage());
            model.addAttribute("fragrances", new ArrayList<FragranceDto>());
        }
        return "perfumetransaction/testapi";
    }
}