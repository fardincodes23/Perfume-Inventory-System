package ca.hccis.perfume.jpa.entity;

import java.util.List;

public class PerfumeTransactionList {

    private List<PerfumeTransaction> transactions;

    public List<PerfumeTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<PerfumeTransaction> transactions) {
        this.transactions = transactions;
    }
}