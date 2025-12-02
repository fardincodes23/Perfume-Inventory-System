
package ca.hccis.perfume.client;
/**
 * DTO for Fragrance data from Fragella API
 * Maps to the JSON response from the external API
 *
 * @author Fardin
 * @since 2025-11-28
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class FragranceDto {

    private String id;
    private String name;
    private String brand;
    private String gender;
    private String year;
    private String country;
    private Double rating;
    private String price;

    @JsonProperty("Image URL")
    private String imageUrl;

    @JsonProperty("Purchase URL")
    private String purchaseUrl;

    @JsonProperty("OilType")
    private String oilType;

    @JsonProperty("Longevity")
    private String longevity;

    @JsonProperty("Sillage")
    private String sillage;

    @JsonProperty("General Notes")
    private String[] generalNotes;

    @JsonProperty("Main Accords")
    private String[] mainAccords;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    public void setPurchaseUrl(String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public String getLongevity() {
        return longevity;
    }

    public void setLongevity(String longevity) {
        this.longevity = longevity;
    }

    public String getSillage() {
        return sillage;
    }

    public void setSillage(String sillage) {
        this.sillage = sillage;
    }

    public String[] getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String[] generalNotes) {
        this.generalNotes = generalNotes;
    }

    public String[] getMainAccords() {
        return mainAccords;
    }

    public void setMainAccords(String[] mainAccords) {
        this.mainAccords = mainAccords;
    }

    @Override
    public String toString() {
        return "FragranceDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", gender='" + gender + '\'' +
                ", rating=" + rating +
                ", price='" + price + '\'' +
                ", oilType='" + oilType + '\'' +
                ", longevity='" + longevity + '\'' +
                ", sillage='" + sillage + '\'' +
                '}';
    }
}