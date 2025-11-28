package ca.hccis.perfume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring configuration for REST client beans
 * Provides RestTemplate for making HTTP calls to external APIs
 */
@Configuration
public class RestClientConfig {

    /**
     * Create a RestTemplate bean for making REST API calls
     *
     * @return RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}