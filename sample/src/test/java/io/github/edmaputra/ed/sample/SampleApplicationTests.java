package io.github.edmaputra.ed.sample;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import io.github.edmaputra.ed.core.constant.Contract;
import io.github.edmaputra.ed.data.predicate.BasePredicate;
import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.model.QEmployee;
import io.github.edmaputra.ed.sample.predicate.EmployeePredicate;
import io.github.edmaputra.ed.sample.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SampleApplicationTests {

}
