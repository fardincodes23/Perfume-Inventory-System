package ca.hccis.entity;

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

}