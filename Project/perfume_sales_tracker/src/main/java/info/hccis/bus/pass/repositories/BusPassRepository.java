package info.hccis.bus.pass.repositories;

import info.hccis.bus.pass.jpa.entity.BusPass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusPassRepository extends CrudRepository<BusPass, Integer> {
    /**
     * Use Spring Data JPA functionality to find a list of bus passes containing the
     * string passed in as a paramter.
     *
     * @param name The name to find
     * @return The list of items
     * @since 20241031
     * @author BJM
     */
    //https://www.baeldung.com/spring-jpa-like-queries
    List<BusPass> findByNameContaining(String name);

}