package ca.hccis.perfume.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @Size(max = 50)
    @NotNull
    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;

    @Size(max = 15)
    @NotNull
    @Column(name = "phoneNumber", nullable = false, length = 15)
    private String phoneNumber;

    @Size(max = 50)
    @NotNull
    @Column(name = "perfumeChoice", nullable = false, length = 50)
    private String perfumeChoice;

    @Size(max = 10)
    @NotNull
    @Column(name = "perfumeSize", nullable = false, length = 10)
    private String perfumeSize;

    @NotNull
    @Column(name = "pricePerBottle", nullable = false)
    private Double pricePerBottle;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "subTotal", nullable = false)
    private Double subTotal;

    @NotNull
    @Column(name = "taxAmount", nullable = false)
    private Double taxAmount;

    @NotNull
    @Column(name = "totalPrice", nullable = false)
    private Double totalPrice;

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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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
                ", totalPrice=" + totalPrice +
                '}';
    }
}
