package io.github.edmaputra.ed.sample.configuration;

import io.github.edmaputra.ed.edbase.exception.CrudOperationException;
import io.github.edmaputra.ed.edbase.model.Gender;
import io.github.edmaputra.ed.edbase.model.MaritalStatus;
import io.github.edmaputra.ed.sample.model.Employee;
import io.github.edmaputra.ed.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DataInitialization {

  @Autowired
  private EmployeeService service;

  private Employee e0, e1, e2, e3, e4, e5;

  public DataInitialization() {
    e0 = new Employee("Bangun",
        "Edma",
        "Saputra",
        Gender.MALE,
        MaritalStatus.MARRIED,
        "Kota Bangun",
        LocalDate.of(1990, Month.AUGUST, 1),
        "085112345678",
        "bangun.edma.saputra@gmail.com",
        326
    );

    e1 = new Employee("Rina",
        "",
        "Wibowo",
        Gender.FEMALE,
        MaritalStatus.SINGLE,
        "Bandung",
        LocalDate.of(1991, Month.SEPTEMBER, 5),
        "0852098754",
        "rina.wibowo@mail.com",
        521
    );

    e2 = new Employee("Alex",
        "Maningger",
        "Sanchez",
        Gender.MALE,
        MaritalStatus.DIVORCEE,
        "Espanyol",
        LocalDate.of(1988, Month.FEBRUARY, 16),
        "085555550000",
        "maningger@mail.com",
        321
    );

    e3 = new Employee("Ahmad",
        "",
        "Yusuf",
        Gender.MALE,
        MaritalStatus.SINGLE,
        "Makassar",
        LocalDate.of(1982, Month.MAY, 21),
        "08881234578",
        "ahmad.yusuf@mail.com",
        20
    );

    e4 = new Employee("Cindy",
        "Putri",
        "Mistika",
        Gender.FEMALE,
        MaritalStatus.WIDOWER,
        "Jakarta",
        LocalDate.of(1995, Month.NOVEMBER, 15),
        "08660123654",
        "cindy.mistika@mail.com",
        66
    );

    e5 = new Employee("Robert",
        "Cristo",
        "Pasaribu",
        Gender.MALE,
        MaritalStatus.MARRIED,
        "Medan",
        LocalDate.of(1972, Month.DECEMBER, 25),
        "085555551111",
        "robert.pasaribu@mail.com",
        466
    );
  }

  //  @PostConstruct
  public void save() throws CrudOperationException {
    service.add(e0);
    service.add(e1);
    service.add(e2);
    service.add(e3);
  }

}
