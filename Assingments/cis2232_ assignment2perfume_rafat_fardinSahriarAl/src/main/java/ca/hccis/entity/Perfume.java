package ca.hccis.entity;

import ca.hccis.util.CisUtility;
import com.google.gson.Gson;


/*
 * @author:Fardin Sahriar Al Rafat
 * @since 16092025
 * project: Working of a project which tracks sales of a perfume shop
 * */


public class Perfume {

    //Constant value
    private static final double TAX_RATE = 0.10;
    private static final double MIDDLE_SIZE_BOTTLE_RATE = 40;
    private static final double LARGE_SIZE_BOTTLE_RATE = 60;
    private static final String PERFUME_LIST
            = "\n-------------------------\n"
            + "- Perfume Shop -\n"
            + "Enter perfume choice (1 to 3)==>\n"
            + "- 1) Dior\n"
            + "- 2) Chanel\n"
            + "- 3) Gucci\n"
            + "-------------------------\n";


    private String customerName;
    private String perfumeName;
    private int size;
    private Double pricePerBottle;
    private int quantity;


    private double subTotal;
    private double taxAmount;
    private double totalPrice;


    // default constructor
    public Perfume() {
    }


    /*
     * @author fsar
     * @since 16092025
     * topic: taking information from customer
     * */


    public void getInformation() {
        customerName = CisUtility.getInputString("Enter customer name: ");

        int perfumeChoice = CisUtility.getInputInt(PERFUME_LIST);

        switch (perfumeChoice) {
            case 1:
                perfumeName = "Dior";
                break;
            case 2:
                perfumeName = "Chanel";
                break;
            case 3:
                perfumeName = "Gucci";
                break;

        }

        //choose size of the perfume

        size = CisUtility.getInputInt("Enter perfume size: 1) 90ml or 2) 120ml . Select 1 OR 2");
        if (size == 1) {
            pricePerBottle = MIDDLE_SIZE_BOTTLE_RATE;
        } else {
            pricePerBottle = LARGE_SIZE_BOTTLE_RATE;
        }

        //take the quantity from the customer

        quantity = CisUtility.getInputInt("Enter perfume quantity: ");

        calculateTotalPrice();

    }

    public double calculateTotalPrice() {
        subTotal = pricePerBottle * quantity;
        taxAmount = subTotal * TAX_RATE;
        totalPrice = subTotal + taxAmount;
        return totalPrice;
    }
    //Getters and Setters

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPerfumeName() {
        return perfumeName;
    }

    public void setPerfumeName(String perfumeName) {
        this.perfumeName = perfumeName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerBottle() {
        return pricePerBottle;
    }

    public void setPricePerBottle(Double pricePerBottle) {
        this.pricePerBottle = pricePerBottle;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //JSON Serialization using Gson
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    //ToString method for displaying all data
    @Override
    public String toString() {
        return "Perfume purchase details: \n" +
                "Customer Name: " + customerName + "\n" +
                "Perfume Name: " + perfumeName + "\n" +
                "Perfume Size: " + size + "\n" +
                "Perfume Quantity: " + quantity + "\n" +
                "Total Price: " + totalPrice + "\n" +
                "Total Tax Amount: " + taxAmount + "\n";

    }


}
