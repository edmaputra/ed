package io.github.edmaputra.ed.sample.repository;

import io.github.edmaputra.ed.edbase.repository.BaseRepository;
import io.github.edmaputra.ed.sample.model.Item;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends BaseRepository<Item, UUID> {
}
