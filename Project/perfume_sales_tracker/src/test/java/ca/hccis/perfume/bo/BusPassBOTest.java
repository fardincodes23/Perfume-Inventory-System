package ca.hccis.perfume.bo;

import ca.hccis.perfume.jpa.entity.BusPass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BusPassBOTest {

    @Test
    void calculateBusPassCost_1length_nonRural_regularType() {

        BusPass busPass = new BusPass();
        busPass.setLengthOfPass(1);
        busPass.setValidForRuralRoute(false);
        busPass.setPassType(3); //regular
        double actual = BusPassBO.calculateBusPassCost(busPass);
        double expected = 1.0;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateBusPassCost_21_length_nonRural_regularType() {

        BusPass busPass = new BusPass();
        busPass.setLengthOfPass(21);
        busPass.setValidForRuralRoute(false);
        busPass.setPassType(3); //regular
        double actual = BusPassBO.calculateBusPassCost(busPass);
        double expected = 20.50;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateBusPassCost_20_length_nonRural_regularType() {

        BusPass busPass = new BusPass();
        busPass.setLengthOfPass(20);
        busPass.setValidForRuralRoute(false);
        busPass.setPassType(3); //regular
        double actual = BusPassBO.calculateBusPassCost(busPass);
        double expected = 20;
        Assertions.assertEquals(expected, actual);
    }

}