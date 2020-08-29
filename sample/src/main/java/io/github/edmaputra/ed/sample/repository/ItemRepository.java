package io.github.edmaputra.ed.sample.repository;

import io.github.edmaputra.ed.edpos.repository.BaseItemRepository;
import io.github.edmaputra.ed.sample.model.Category;
import io.github.edmaputra.ed.sample.model.Item;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends BaseItemRepository<Item, Category, UUID> {
}
