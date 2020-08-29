package io.github.edmaputra.ed.sample.repository;

import io.github.edmaputra.ed.edpos.repository.BaseCategoryRepository;
import io.github.edmaputra.ed.sample.model.Category;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends BaseCategoryRepository<Category, UUID> {
}
