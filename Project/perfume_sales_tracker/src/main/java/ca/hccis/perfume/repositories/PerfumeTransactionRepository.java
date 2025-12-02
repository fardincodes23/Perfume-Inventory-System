package ca.hccis.perfume.repositories;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfumeTransactionRepository extends JpaRepository<PerfumeTransaction, Integer> {
    List<PerfumeTransaction> findByCustomerNameContaining(String customerName);

    List<PerfumeTransaction> findByPerfumeChoiceContaining(String perfumeChoice);

    List<PerfumeTransaction> findByCustomerNameContainingAndPerfumeChoiceContaining(String name, String choice);

}