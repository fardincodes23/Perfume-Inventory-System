package ca.hccis.perfume.bo;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;

/**
 * Business Object to handle calculations.
 * This separates logic from the database entity.
 */
public class PerfumeBo {

    /**
     * Static helper method to perform calculations on a transaction.
     * @param pt The transaction entity to modify
     */
    public static void calculate(PerfumeTransaction pt) {
        // Safety check
        if (pt.getQuantity() == null || pt.getPricePerBottle() == null) {
            pt.setSubTotal(0.0);
            pt.setTaxAmount(0.0);
            pt.setTotal(0.0);
            return;
        }

        double quantity = pt.getQuantity();
        double price = pt.getPricePerBottle();

        // 1. Calculate Subtotal
        double sub = quantity * price;

        // 2. Calculate Tax (10%)
        final double TAX_RATE = 0.10;
        double tax = sub * TAX_RATE;

        // 3. Calculate Total
        double total = sub + tax;

        // Update the entity with the new values
        pt.setSubTotal(sub);
        pt.setTaxAmount(tax);
        pt.setTotal(total);
    }
}