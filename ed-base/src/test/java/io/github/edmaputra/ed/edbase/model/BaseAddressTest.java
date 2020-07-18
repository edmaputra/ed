package io.github.edmaputra.ed.edbase.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BaseAddressTest {

  private BaseAddress<String> impl;

  @BeforeEach
  void init() {
    impl = new BaseAddressImpl();
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
    impl = new BaseAddressImpl(
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

  private static class BaseAddressImpl extends BaseAddress<String> {

    public BaseAddressImpl() {
    }

    public BaseAddressImpl(String street, String city, String province, String zipCode, String country) {
      super(street, city, province, zipCode, country);
    }
  }

}
