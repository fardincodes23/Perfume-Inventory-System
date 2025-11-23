package ca.hccis.perfume.repositories;
import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This interface automatically provides ALL basic CRUD methods:
// save(), findById(), findAll(), delete(), etc.
public interface PerfumeTransactionRepository extends JpaRepository<PerfumeTransaction, Integer> {

    // You don't need to write any code in here!

    // OPTIONAL requirement (example for FindBy non-primary key field):
    List<PerfumeTransaction> findByCustomerNameContaining(String customerName);

    //find by perfume choice
    List<PerfumeTransaction> findByPerfumeChoiceContaining(String perfumeChoice);

    //find by perfume choice and customer name
    List<PerfumeTransaction> findByCustomerNameContainingAndPerfumeChoiceContaining(String name, String choice);

}