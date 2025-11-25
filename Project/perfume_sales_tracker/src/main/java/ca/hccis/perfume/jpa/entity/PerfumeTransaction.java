package ca.hccis.perfume.jpa.entity;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Entity class representing a perfume sales transaction.
 * @author Fardin
 * @since 20251024
 */
@Entity
@Table(name = "PerfumeTransaction")
public class PerfumeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "transactionDate", nullable = false, length = 20)
    private String transactionDate;

    @Size(min = 2, max = 50, message = "Customer Name must be between 2 and 50 characters.")
    @NotNull(message = "Customer Name is required.")
    private String customerName;

    @Size(max = 15, message = "Phone Number format is invalid.")
    @NotNull(message = "Phone Number is required.")
    private String phoneNumber;

    @NotNull(message = "Perfume Choice cannot be empty.")
    private String perfumeChoice;

    @Size(max = 10)
    @NotNull
    @Column(name = "perfumeSize", nullable = false, length = 10)
    private String perfumeSize;

    @DecimalMin(value = "0.01", message = "Price must be greater than zero.")
    @NotNull(message = "Price per bottle is required.")
    private double pricePerBottle;

    @Min(value = 1, message = "Quantity must be at least 1.")
    @Max(value = 99, message = "Quantity cannot exceed 99.")
    @NotNull(message = "Quantity is required.")
    private int quantity;


    @Column(name = "subTotal", nullable = false)
    private Double subTotal;


    @Column(name = "taxAmount", nullable = false)
    private Double taxAmount;

    @Column(name = "total", nullable = false)
    private Double total;

    // ====== Getters and Setters ======


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPerfumeChoice() {
        return perfumeChoice;
    }

    public void setPerfumeChoice(String perfumeChoice) {
        this.perfumeChoice = perfumeChoice;
    }

    public String getPerfumeSize() {
        return perfumeSize;
    }

    public void setPerfumeSize(String perfumeSize) {
        this.perfumeSize = perfumeSize;
    }

    public Double getPricePerBottle() {
        return pricePerBottle;
    }

    public void setPricePerBottle(Double pricePerBottle) {
        this.pricePerBottle = pricePerBottle;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PerfumeTransaction{" +
                "id=" + id +
                ", transactionDate='" + transactionDate + '\'' +
                ", customerName='" + customerName + '\'' +
                ", perfumeChoice='" + perfumeChoice + '\'' +
                ", perfumeSize='" + perfumeSize + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + total +
                '}';
    }
}
