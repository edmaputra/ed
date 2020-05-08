package io.github.edmaputra.ed.sample.controller;

import io.github.edmaputra.ed.sample.model.Address;
import io.github.edmaputra.ed.sample.service.AddressService;
import io.github.edmaputra.ed.web.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController<Address, Long> {

  public AddressController(AddressService service) {
    super(service);
  }
}
