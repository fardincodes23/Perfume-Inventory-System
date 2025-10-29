package ca.hccis.entity;

import ca.hccis.exception.PerfumeException;
import ca.hccis.util.CisUtility;
import com.google.gson.Gson;


/**
 * @author:Fardin Sahriar Al Rafat
 * @since 16092025
 * project: Working of a project which tracks sales of a perfume shop
 *
 */


public class Perfume {

    //Constant value
    public static final double TAX_RATE = 0.10;
    public static final double MIDDLE_SIZE_BOTTLE_RATE = 40;
    public static final double LARGE_SIZE_BOTTLE_RATE = 60;
    public static final String PERFUME_LIST
            = "\n"
            + "- Perfume Shop -\n"
            + "Enter perfume choice (1 to 3)==>\n"
            + "- 1) Dior\n"
            + "- 2) Chanel\n"
            + "- 3) Gucci\n"
            + "\n";


    private String customerName;
    private String phoneNumber;
    private int perfumeName;
    private String perfumeChoice;
    private int sizeInput;
    private String perfumeSize;
    public Double pricePerBottle;
    private int quantity;


    private double subTotal;
    private double taxAmount;
    private double totalPrice;


    // default constructor
    public Perfume() {
    }


    /**
     * @author fsar
     * @since 16092025
     * topic: taking information from customer
     *
     */


    CisUtility cisUtility = new CisUtility();     //creating an CisUtility object

    public void getInformation(CisUtility cisUtility) {


        customerName = cisUtility.getInputString("Enter customer name: ");
        phoneNumber = cisUtility.getInputString("Enter phone number: ");

        /*
         * Task: adding validation so that user give a numeric input for the Perfume Name (1 ,2 or 3)
         * @author Fardin
         * @since 2025-10-29
         *
         * */

        boolean validation = false;

        do {
            try {
                setPerfumeName(Integer.parseInt(cisUtility.getInputString(PERFUME_LIST)));
                validation = true;
            } catch (PerfumeException e) {
                cisUtility.display(("Enter perfume choice (1 to 3)"));
            } catch (NumberFormatException e) {
                cisUtility.display(("No String is accepted. Enter perfume choice (1 to 3)"));
            }
        } while (!validation);

        switch (perfumeName) {
            case 1:
                perfumeChoice = "Dior";
                break;
            case 2:
                perfumeChoice = "Chanel";
                break;
            case 3:
                perfumeChoice = "Gucci";
                break;

        }

        /*
         * Task: adding validation so that user give a numeric input for the Perfume Size (1 for medium size bottle or 2 for large size bottle)
         *
         * @author Fardin
         * @since 2025-10-29
         *
         * */

        do {
            sizeInput = cisUtility.getInputInt("Enter perfume size: 1) 90ml or 2) 120ml . Select 1 OR 2");
            if (sizeInput == 1) {
                pricePerBottle = MIDDLE_SIZE_BOTTLE_RATE;
                perfumeSize = "90ml";
            } else if (sizeInput == 2) {
                pricePerBottle = LARGE_SIZE_BOTTLE_RATE;
                perfumeSize = "120ml";

            } else {
                System.out.println("Please enter 1 or 2 got the size selection");

            }
        } while (sizeInput < 1 || sizeInput > 2);


        //take the quantity from the customer

        quantity = cisUtility.getInputInt("Enter perfume quantity: ");

        calculateTotalPrice();

    }

    public void calculateTotalPrice() {
        subTotal = pricePerBottle * quantity;
        taxAmount = subTotal * TAX_RATE;
        totalPrice = subTotal + taxAmount;
    }


    //Getters and Setters

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPerfumeName() {
        return perfumeName;
    }


    /*
     * Task: system will throw exception if not a valid input
     * @author Fardin
     * @since 2025-10-29
     *
     * */

    public void setPerfumeName(int perfumeName) throws PerfumeException {

        if (perfumeName < 1 || perfumeName > 3) {
            throw new PerfumeException();
        } else {
            this.perfumeName = perfumeName;
        }
    }

    public CisUtility getCisUtility() {
        return cisUtility;
    }

    public void setCisUtility(CisUtility cisUtility) {
        this.cisUtility = cisUtility;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPerfumeSize() {
        return perfumeSize;
    }

    public void setPerfumeSize(String perfumeSize) {
        this.perfumeSize = perfumeSize;
    }

    public int getSizeInput() {
        return sizeInput;
    }

    public String getPerfumeChoice() {
        return perfumeChoice;
    }

    public void setPerfumeChoice(String perfumeChoice) {
        this.perfumeChoice = perfumeChoice;
    }

    public void setSizeInput(int sizeInput) throws PerfumeException {
        if (sizeInput > 2 || sizeInput < 0) {

            throw new PerfumeException();

        } else {
            this.sizeInput = sizeInput;

        }
    }

    public int getQuantity() {
        return quantity;
    }


    /**
     * ADDED AN EXCEPTION CONDITION TO TEST VALID RANGE FOR THE QUANTITY
     *
     * @author: fsar
     * @since 20251018
     *
     */

    public void setQuantity(int quantity) throws PerfumeException {

        if (quantity > 50 || quantity < 0) {
            throw new PerfumeException();
        } else {
            this.quantity = quantity;
        }
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
                "Phone Number: " + phoneNumber + "\n" +
                "Perfume Choice: " + perfumeChoice + "\n" +
                "Perfume Size: " + perfumeSize + "\n" +
                "Perfume Quantity: " + quantity + "\n" +
                "Subtotal: " + subTotal + "\n" +
                "Total Tax Amount: " + taxAmount + "\n" +
                "Total Price: " + totalPrice + "\n";


    }


}
