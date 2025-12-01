package ca.hccis.perfume.bo;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PerfumeBoTest {

    @Test
    void testCalculate_Standard() {
        // Setup
        PerfumeTransaction pt = new PerfumeTransaction();
        pt.setQuantity(2);
        pt.setPricePerBottle(100.00);

        // Execute (Calling the BO!)
        PerfumeBo.calculate(pt);

        // Assert
        assertEquals(200.00, pt.getSubTotal());
        assertEquals(20.00, pt.getTaxAmount());
        assertEquals(220.00, pt.getTotal());
    }

    @Test
    void testCalculate_Zero() {
        // Setup
        PerfumeTransaction pt = new PerfumeTransaction();
        pt.setQuantity(0);
        pt.setPricePerBottle(50.00);

        // Execute
        PerfumeBo.calculate(pt);

        // Assert
        assertEquals(0.0, pt.getTotal());
    }
}