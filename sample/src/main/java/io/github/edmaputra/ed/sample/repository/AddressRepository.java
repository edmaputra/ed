package io.github.edmaputra.ed.sample.repository;

import io.github.edmaputra.ed.data.repository.BaseRepository;
import io.github.edmaputra.ed.sample.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {
}
