package backend.repository;

import backend.domain.challenge.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository{
    Category save(Category category);
    List<Category> findAll();
    Optional<Category> findByCategoryName(String categoryName);
    Optional<Category> findByCategoryId(Long categoryId);
}
