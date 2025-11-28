package ca.hccis.perfume.repositories;

import ca.hccis.perfume.jpa.entity.CodeValue;
import ca.hccis.perfume.jpa.entity.CodeValueId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// The second argument MUST be the type of the @EmbeddedId (CodeValueId)
public interface CodeValueRepository extends CrudRepository<CodeValue, CodeValueId> {

    // HQL uses 'c.id.codeTypeId' to drill into the composite key
    @Query("SELECT c FROM CodeValue c WHERE c.id.codeTypeId = ?1 ORDER BY c.sortOrder ASC")
    List<CodeValue> findByCodeTypeId(Integer codeTypeId);
}