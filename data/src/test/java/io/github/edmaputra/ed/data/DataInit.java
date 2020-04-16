package io.github.edmaputra.ed.data;

import io.github.edmaputra.ed.core.model.Gender;
import io.github.edmaputra.ed.core.model.MaritalStatus;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;

public class DataInit {

  private Employee e0, e1, e2, e3, e4, e5;

  public DataInit() {
    e0 = new Employee("Bangun",
        "Edma",
        "Saputra",
        Gender.MALE,
        MaritalStatus.MARRIED,
        "Kota Bangun",
        LocalDate.of(1990, Month.AUGUST, 1),
        "085112345678",
        "bangun.edma.saputra@gmail.com"
    );

    e1 = new Employee("Rina",
        "",
        "Wibowo",
        Gender.FEMALE,
        MaritalStatus.SINGLE,
        "Bandung",
        LocalDate.of(1991, Month.SEPTEMBER, 5),
        "08520987654",
        "rina.wibowo@mail.com"
    );

    e2 = new Employee("Alex",
        "Maningger",
        "Sanchez",
        Gender.MALE,
        MaritalStatus.DIVORCEE,
        "Espanyol",
        LocalDate.of(1988, Month.FEBRUARY, 16),
        "085555550000",
        "maningger@mail.com"
    );

    e3 = new Employee("Ahmad",
        "",
        "Yusuf",
        Gender.MALE,
        MaritalStatus.SINGLE,
        "Makassar",
        LocalDate.of(1982, Month.MAY, 21),
        "088812345678",
        "ahmad.yusuf@mail.com"
    );

    e4 = new Employee("Cindy",
        "Putri",
        "Mistika",
        Gender.FEMALE,
        MaritalStatus.WIDOWER,
        "Jakarta",
        LocalDate.of(1995, Month.NOVEMBER, 15),
        "08660987654",
        "cindy.mistika@mail.com"
    );

    e5 = new Employee("Robert",
        "Cristo",
        "Pasaribu",
        Gender.MALE,
        MaritalStatus.MARRIED,
        "Medan",
        LocalDate.of(1972, Month.DECEMBER, 25),
        "085555551111",
        "robert.pasaribu@mail.com"
    );
  }

  public Employee getEmployee(int id) {
    switch (id) {
      case 0:
        return e0;
      case 1:
        return e1;
      case 2:
        return e2;
      case 3:
        return e3;
      case 4:
        return e4;
      default:
        return e5;
    }
  }

  public Employee getEmployeeRandomize() {
    Random random = new Random();
    int i = random.nextInt(6);
    switch (i) {
      case 0:
        return e0;
      case 1:
        return e1;
      case 2:
        return e2;
      case 3:
        return e3;
      case 4:
        return e4;
      default:
        return e5;
    }
  }

  public List<Employee> getAllEmployee() {
    return List.of(e0, e1, e2, e3, e4, e5);
  }
}
