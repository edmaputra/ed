package io.github.edmaputra.ed.sample.repository;

import io.github.edmaputra.ed.edbase.repository.BaseRepository;
import io.github.edmaputra.ed.sample.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, UUID> {
}
