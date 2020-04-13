package io.github.edmaputra.ed.data.repository;

import io.github.edmaputra.ed.data.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepositoryEmployee extends BaseRepository<Employee, Long> {
}
