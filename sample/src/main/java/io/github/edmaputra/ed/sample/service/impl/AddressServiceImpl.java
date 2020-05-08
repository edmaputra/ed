package io.github.edmaputra.ed.sample.service.impl;

import io.github.edmaputra.ed.data.service.impl.BaseServiceImpl;
import io.github.edmaputra.ed.sample.model.Address;
import io.github.edmaputra.ed.sample.repository.AddressRepository;
import io.github.edmaputra.ed.sample.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, Long> implements AddressService {

  public AddressServiceImpl(AddressRepository repository) {
    super(repository);
  }

}
