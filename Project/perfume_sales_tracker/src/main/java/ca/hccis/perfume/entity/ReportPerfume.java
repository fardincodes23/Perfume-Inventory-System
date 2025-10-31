package ca.hccis.perfume.entity;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the reports.
 * @author bjmaclean
 * @since 20241010
 */
public class ReportPerfume {
    private String perfumeChoice;

    private ArrayList<PerfumeTransaction> perfumeTransaction;

    public String getPerfumeChoice() {
        return perfumeChoice;
    }

    public void setPerfumeChoice(String perfumeChoice) {
        this.perfumeChoice = perfumeChoice;
    }

    public ArrayList<PerfumeTransaction> getPerfumeTransaction() {
        return perfumeTransaction;
    }

    public void setPerfumeTransaction(ArrayList<PerfumeTransaction> perfumeTransaction) {
        this.perfumeTransaction = perfumeTransaction;
    }
}
