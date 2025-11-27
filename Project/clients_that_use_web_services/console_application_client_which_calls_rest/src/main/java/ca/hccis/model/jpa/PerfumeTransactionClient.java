package ca.hccis.model.jpa; // Adjust package to match your structure

import java.util.Scanner;

public class PerfumeTransactionClient {

    private Integer id;
    private double quantity;
    private double pricePerBottle;
    private String customerName;
    private String perfumeChoice;
    // These are calculated by the server, but we might receive them back
    private double subTotal;
    private double taxAmount;
    private double total;

    /**
     * Helper method to get input from the console user.
     */
    public void getInformation() {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Enter Perfume Transaction Details ---");

        System.out.print("Enter Customer Name: ");
        this.customerName = input.nextLine();

        System.out.print("Enter Perfume Choice: ");
        this.perfumeChoice = input.nextLine();

        // Loop for valid double input for Quantity
        while (true) {
            System.out.print("Enter Quantity: ");
            try {
                this.quantity = Double.parseDouble(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }

        // Loop for valid double input for Price
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
                ", perfume='" + perfumeChoice + '\'' +
                ", qty=" + quantity +
                ", price=" + pricePerBottle +
                ", total=" + total +
                '}';
    }
}