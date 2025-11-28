package ca.hccis.perfume.bo;

import ca.hccis.perfume.dao.PerfumeTransactionDAO;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import ca.hccis.perfume.util.CisUtilityFile;

import java.util.ArrayList;

public class PerfumeTransactionBO {

    public static ArrayList<PerfumeTransaction> processSelectAllByPerfumeChoice(String perfumeChoice) {

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        PerfumeTransactionDAO perfumeTransactionDAO = new PerfumeTransactionDAO();
        ArrayList<PerfumeTransaction> records = perfumeTransactionDAO.selectAllByPerfumeChoice(perfumeChoice);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("perfumeChoiceReport", records);

        return records;
    }

}