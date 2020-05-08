package io.github.edmaputra.ed.sample.repository;

import io.github.edmaputra.ed.data.repository.BaseRepository;
import io.github.edmaputra.ed.sample.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Long> {
}
