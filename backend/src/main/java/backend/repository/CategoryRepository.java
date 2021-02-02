package backend.repository;

import backend.domain.Category;

import java.util.List;

public interface CategoryRepository{
        Category save(Category category);
        List<Category> findAll();
}
