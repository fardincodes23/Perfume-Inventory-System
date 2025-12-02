/**
 * Write to file
 *
 * @author Fardin
 * @since 2025-11-14
 */

package ca.hccis.perfume.bo;

import ca.hccis.perfume.dao.PerfumeTransactionDAO;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import ca.hccis.perfume.util.CisUtilityFile;

import java.util.ArrayList;

public class PerfumeTransactionBO {

    public static ArrayList<PerfumeTransaction> processSelectAllByPerfumeChoice(String perfumeChoice) {
        PerfumeTransactionDAO perfumeTransactionDAO = new PerfumeTransactionDAO();
        ArrayList<PerfumeTransaction> records = perfumeTransactionDAO.selectAllByPerfumeChoice(perfumeChoice);

        CisUtilityFile.writeReportToFile("perfumeChoiceReport", records);

        return records;
    }

}