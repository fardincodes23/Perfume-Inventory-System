package ca.hccis.perfume.bo;

import ca.hccis.perfume.jpa.entity.BusPass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BusPassValidationBO {

    public ArrayList<String> validateStartDate(BusPass busPass) {

        ArrayList<String> errors = new ArrayList<>();

        String startDate = busPass.getStartDate();
        if (startDate.length() != 10) {
            errors.add("Start date must be 10 length");
        }

        if (errors.isEmpty()) {
            try {
                LocalDate localDateStartDate = LocalDate.parse(startDate);

                LocalDate currentDate = LocalDate.now();
                LocalDate oneMonthLater = currentDate.plusMonths(1);

                if (localDateStartDate.isAfter(oneMonthLater)) {
                    errors.add("Start date is more than one month after the current date");
                }
            } catch (Exception e) {
                errors.add("Could not parse start date");
            }
        }
        //TODO Add validation to ensure start date is not > 1 month in the future.

        return errors;
    }

}
