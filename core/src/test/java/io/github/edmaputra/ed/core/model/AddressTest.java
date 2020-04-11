package io.github.edmaputra.ed.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressTest {

  private Address impl;

  @BeforeEach
  void init() {
    impl = new AddressImpl();
  }

  @Test
  void givenAddress_whenInit_thenShouldReturnDefaultValue() {
    assertThat(impl.getStreet()).isNull();
    assertThat(impl.getCity()).isNull();
    assertThat(impl.getProvince()).isNull();
    assertThat(impl.getCountry()).isNull();
    assertThat(impl.getZipCode()).isNull();
  }

  @Test
  void givenAddress_whenSetWithSetter_thenShouldReturnCorrectValue() {
    impl.setStreet("Cigending Street No. 5");
    impl.setCity("Bandung");
    impl.setProvince("East Java");
    impl.setCountry("Indonesia");
    impl.setZipCode("40618");

    assertThat(impl.getStreet()).isEqualTo("Cigending Street No. 5");
    assertThat(impl.getCity()).isEqualTo("Bandung");
    assertThat(impl.getProvince()).isEqualTo("East Java");
    assertThat(impl.getCountry()).isEqualTo("Indonesia");
    assertThat(impl.getZipCode()).isEqualTo("40618");
  }

  @Test
  void givenAddress_whenInitWithInjectedValue_thenShouldReturnCorrectValue() {
    impl = new AddressImpl(
      "Antapani St. No. 100",
      "Bandung",
      "East Java",
      "40110",
      "Indonesia"
    );

    assertThat(impl.getStreet()).isEqualTo("Antapani St. No. 100");
    assertThat(impl.getCity()).isEqualTo("Bandung");
    assertThat(impl.getProvince()).isEqualTo("East Java");
    assertThat(impl.getCountry()).isEqualTo("Indonesia");
    assertThat(impl.getZipCode()).isEqualTo("40110");
  }

  private static class AddressImpl extends Address<String> {

    public AddressImpl() {
    }

    public AddressImpl(String street, String city, String province, String zipCode, String country) {
      super(street, city, province, zipCode, country);
    }
  }

}
