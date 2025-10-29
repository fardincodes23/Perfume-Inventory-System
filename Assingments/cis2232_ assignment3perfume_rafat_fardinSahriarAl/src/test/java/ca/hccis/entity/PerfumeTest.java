/**
 * Unit tests for the Perfume class.
 * Following Test Driven Development principles.
 * @author fsar
 * @since 20251017
 *
 */

package ca.hccis.entity;

import ca.hccis.exception.PerfumeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerfumeTest {
    Perfume perfume;

    @BeforeEach
    void setUp() {
        System.out.println("Inside setup method");
        perfume = new Perfume();
    }

    @Test
    void calculateTotalPrice_middleSizeBottle_subTotal() {

        perfume.setPricePerBottle(40.00);
        perfume.setQuantity(2);
        perfume.calculateTotalPrice();
        double actual = perfume.getSubTotal();

        Assertions.assertEquals(80.00,actual);
    }

    @Test
    void calculateTotalPrice_middleSizeBottle_taxAmount() {

        perfume.setPricePerBottle(40.00);
        perfume.setQuantity(2);
        perfume.calculateTotalPrice();
        double actual = perfume.getTaxAmount();

        Assertions.assertEquals(8.00,actual);
    }

    @Test
    void calculateTotalPrice_middleSizeBottle_totalPrice() {

        perfume.setPricePerBottle(40.00);
        perfume.setQuantity(2);
        perfume.calculateTotalPrice();
        double actual = perfume.getTotalPrice();

        Assertions.assertEquals(88.00,actual);
    }

    @Test
    void calculateTotalPrice_largeSizeBottle_totalPrice() {

        perfume.setPricePerBottle(60.00);
        perfume.setQuantity(1);
        perfume.calculateTotalPrice();
        double actual = perfume.getTotalPrice();

        Assertions.assertEquals(66,actual);
    }

    @Test
    void calculateTotalPrice_negativeQuantity_throwsException() {

        Assertions.assertThrows(PerfumeException.class, () -> {
           perfume.setQuantity(-1);

        });
    }
    @Test
    void calculateTotalPrice_quantityOver50_throwsException() {

        Assertions.assertThrows(PerfumeException.class, () -> {
            perfume.setQuantity(51);

        });
    }

    @Test
    void calculateTotalPrice_sizeOver2_throwsException() {

        Assertions.assertThrows(PerfumeException.class, () -> {
            perfume.setSizeInput(5);

        });
    }

    @Test
    void calculateTotalPrice_negativeSize_throwsException() {

        Assertions.assertThrows(PerfumeException.class, () -> {
            perfume.setSizeInput(-1);

        });
    }

    @Test
    void calculateTotalPrice_totalGreaterThanSubTotal() {

        perfume.setPricePerBottle(60.00);
        perfume.setQuantity(1);
        perfume.calculateTotalPrice();
        double subTotal = perfume.getSubTotal();
        double totalPrice =  perfume.getTotalPrice();

        Assertions.assertTrue(totalPrice > subTotal);


    }

    @Test
    void calculateTotalPrice_subTotalGreaterThanTaxAmount() {

        perfume.setPricePerBottle(60.00);
        perfume.setQuantity(1);
        perfume.calculateTotalPrice();
        double subTotal = perfume.getSubTotal();
        double taxAmount =  perfume.getTaxAmount();

        Assertions.assertTrue(subTotal > taxAmount);


    }





}