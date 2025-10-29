package ca.hccis.perfume.repositories;

import ca.hccis.perfume.jpa.entity.CodeValue;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends CrudRepository<CodeValue, Integer> {
        List<CodeValue> findByCodeTypeId(Integer codeTypeId);
}