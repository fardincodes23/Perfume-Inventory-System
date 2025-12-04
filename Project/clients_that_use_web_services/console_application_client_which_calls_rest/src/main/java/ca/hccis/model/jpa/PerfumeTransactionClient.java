package ca.hccis.model.jpa;

import java.util.Scanner;

public class PerfumeTransactionClient {

    private Integer id = 0;
    private String transactionDate;
    private String phoneNumber;
    private double quantity;
    private double pricePerBottle;
    private String customerName;
    private String perfumeChoice;
    private String perfumeSize;
    private double subTotal;
    private double taxAmount;
    private double total;

    /**
     * Helper method to get input from the console user.
     */
    public void getInformation() {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Enter Perfume Transaction Details ---");

        System.out.print("Enter Transaction Date (YYYY-MM-DD): ");
        this.transactionDate = input.nextLine();
        System.out.print("Enter Customer Name: ");
        this.customerName = input.nextLine();

        System.out.print("Enter Phone Number: ");
        this.phoneNumber = input.nextLine();

        System.out.print("Enter Perfume Choice: ");
        this.perfumeChoice = input.nextLine();
        System.out.println("Enter Perfume Size: ");
        this.perfumeSize = input.nextLine();

        while (true) {
            System.out.print("Enter Quantity: ");
            try {
                this.quantity = Double.parseDouble(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }

        while (true) {
            System.out.print("Enter Price Per Bottle: ");
            try {
                this.pricePerBottle = Double.parseDouble(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    // --- Getters and Setters ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTransactionDate() { return transactionDate; }
    public void setTransactionDate(String transactionDate) { this.transactionDate = transactionDate; }

    public String getPerfumeSize() {
        return perfumeSize;
    }

    public void setPerfumeSize(String perfumeSize) {
        this.perfumeSize = perfumeSize;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public double getPricePerBottle() { return pricePerBottle; }
    public void setPricePerBottle(double pricePerBottle) { this.pricePerBottle = pricePerBottle; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPerfumeChoice() { return perfumeChoice; }
    public void setPerfumeChoice(String perfumeChoice) { this.perfumeChoice = perfumeChoice; }

    public double getSubTotal() { return subTotal; }
    public void setSubTotal(double subTotal) { this.subTotal = subTotal; }

    public double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(double taxAmount) { this.taxAmount = taxAmount; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "PerfumeTransaction {" +
                "id=" + id +
                ", customer='" + customerName + '\'' +
                ", date='" + transactionDate + '\'' +
                ", phone='" + phoneNumber + '\'' +
                ", perfume='" + perfumeChoice + '\'' +
                ", qty=" + quantity +
                ", price=" + pricePerBottle +
                ", total=" + total +
                '}';
    }
}