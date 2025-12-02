/**
 * Business Object to handle calculations.
 * This separates logic from the database entity.
 *
 * @author Fardin
 * @since 2025-11-01
 */

package ca.hccis.perfume.bo;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;

public class PerfumeBo {

    public static void calculate(PerfumeTransaction pt) {
        if (pt.getQuantity() == null || pt.getPricePerBottle() == null) {
            pt.setSubTotal(0.0);
            pt.setTaxAmount(0.0);
            pt.setTotal(0.0);
            return;
        }

        double quantity = pt.getQuantity();
        double price = pt.getPricePerBottle();
        double sub = quantity * price;
        final double TAX_RATE = 0.10;
        double tax = sub * TAX_RATE;
        double total = sub + tax;

        pt.setSubTotal(sub);
        pt.setTaxAmount(tax);
        pt.setTotal(total);
    }
}