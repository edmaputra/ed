package io.github.edmaputra.ed.sample.predicate.impl;

import io.github.edmaputra.ed.edbase.predicate.impl.BasePredicateImpl;
import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.predicate.EmployeePredicate;
import org.springframework.stereotype.Component;

@Component
public class EmployeePredicateImpl extends BasePredicateImpl<Employee> implements EmployeePredicate {
}
