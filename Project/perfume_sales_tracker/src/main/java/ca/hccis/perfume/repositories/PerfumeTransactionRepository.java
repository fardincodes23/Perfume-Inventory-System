package ca.hccis.perfume.repositories;
import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface automatically provides ALL basic CRUD methods:
// save(), findById(), findAll(), delete(), etc.
public interface PerfumeTransactionRepository extends JpaRepository<PerfumeTransaction, Integer> {

    // You don't need to write any code in here!

    // OPTIONAL requirement (example for FindBy non-primary key field):
    // List<PerfumeTransaction> findByCustomerName(String customerName);
}