/**
 * Service to interact with Fragella External Perfume API
 * Provides methods to search and retrieve fragrance data
 *
 * JAVA 8 COMPATIBLE VERSION - FIXED API KEY AUTHENTICATION
 *
 * @author Fardin
 * @since 2025-11-28
 */

package ca.hccis.perfume.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerfumeApiClient {

    private static final Logger logger = LoggerFactory.getLogger(PerfumeApiClient.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;

    /**
     * Constructor with dependency injection
     */
    public PerfumeApiClient(RestTemplate restTemplate,
                            @Value("${perfume.api.fragella.base-url}") String baseUrl,
                            @Value("${perfume.api.fragella.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        logger.info("PerfumeApiClient initialized with baseUrl: " + baseUrl);
    }

    /**
     * Search for fragrances by name using Fragella API
     *
     * @param searchTerm The fragrance name to search for
     * @param limit Number of results to return (default 10)
     * @return List of FragranceDto objects matching the search
     */
    public List<FragranceDto> searchFragrances(String searchTerm, int limit) {
        try {
            String url = baseUrl + "/fragrances" +
                    "?search=" + encodeUrl(searchTerm) +
                    "&limit=" + limit;

            logger.info("Calling Fragella API: " + url);
            logger.info("Using API Key: " + (apiKey != null ? "Present" : "MISSING!"));

            HttpHeaders headers = createHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<FragranceDto[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    FragranceDto[].class
            );

            if (response.getBody() != null) {
                logger.info("API returned " + response.getBody().length + " results");
                return Arrays.asList(response.getBody());
            } else {
                logger.warn("API returned null response body");
                return new ArrayList<>();
            }

        } catch (RestClientException e) {
            logger.error("Error calling Fragella API: " + e.getMessage(), e);
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("Unexpected error in searchFragrances: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    /**
     * Search for fragrances with default limit of 10
     */
    public List<FragranceDto> searchFragrances(String searchTerm) {
        return searchFragrances(searchTerm, 10);
    }

    /**
     * Get popular fragrances (no search term)
     */
    public List<FragranceDto> getPopularFragrances(int limit) {
        try {
            String url = baseUrl + "/fragrances?limit=" + limit;
            logger.info("Fetching popular fragrances from: " + url);

            HttpHeaders headers = createHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<FragranceDto[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    FragranceDto[].class
            );

            return response.getBody() != null ? Arrays.asList(response.getBody()) : new ArrayList<>();

        } catch (RestClientException e) {
            logger.error("Error fetching popular fragrances: " + e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    /**
     * Get a specific fragrance by ID
     */
    public FragranceDto getFragranceById(String id) {
        try {
            String url = baseUrl + "/fragrances/" + id;
            logger.info("Fetching fragrance by ID: " + url);

            HttpHeaders headers = createHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<FragranceDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    FragranceDto.class
            );

            return response.getBody();

        } catch (RestClientException e) {
            logger.error("Error fetching fragrance by ID: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Create HTTP headers with API key authentication
     * Fragella requires: x-api-key header
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        if (apiKey != null && !apiKey.isEmpty()) {
            headers.set("x-api-key", apiKey);
            headers.set("X-API-Key", apiKey);
            headers.set("Authorization", "Bearer " + apiKey);
            logger.debug("Added API key to headers");
        } else {
            logger.warn("API key is MISSING! Check your application.properties file!");
        }

        return headers;
    }

    /**
     * URL encode a search term
     */
    private String encodeUrl(String term) {
        if (term == null) {
            return "";
        }
        return term.replaceAll(" ", "%20");
    }
}