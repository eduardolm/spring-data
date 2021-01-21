package br.com.alura.springdata.repository;

import br.com.alura.springdata.model.WorkUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkUnitRepository extends CrudRepository<WorkUnit, Integer> {
}
