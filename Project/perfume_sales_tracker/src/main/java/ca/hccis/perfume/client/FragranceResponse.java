package ca.hccis.perfume.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Wrapper DTO for Fragella API response
 * The API may return results wrapped in a response object
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FragranceResponse {

    private FragranceDto[] data;
    private String message;
    private Boolean success;

    // Getters and Setters
    public FragranceDto[] getData() {
        return data;
    }

    public void setData(FragranceDto[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}