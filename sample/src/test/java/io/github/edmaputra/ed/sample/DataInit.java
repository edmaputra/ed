package io.github.edmaputra.ed.sample;

import io.github.edmaputra.ed.edbase.model.Gender;
import io.github.edmaputra.ed.edbase.model.MaritalStatus;
import io.github.edmaputra.ed.sample.model.Employee;

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
        "08511234578",
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
        "08770123654",
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
