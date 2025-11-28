package ca.hccis.perfume.entity;

import ca.hccis.perfume.jpa.entity.BusPass;

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the reports.
 * @author bjmaclean
 * @since 20241010
 */
public class ReportBusPass {
    private String dateStart;
    private String dateEnd;
    private int minLength;
    private ArrayList<BusPass> busPasses;

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ArrayList<BusPass> getBusPasses() {
        return busPasses;
    }

    public void setBusPasses(ArrayList<BusPass> busPasses) {
        this.busPasses = busPasses;
    }
}
