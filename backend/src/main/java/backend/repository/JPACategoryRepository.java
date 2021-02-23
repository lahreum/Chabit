package backend.repository;

import backend.domain.challenge.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPACategoryRepository implements CategoryRepository{

    private final EntityManager entityManager;

    @Override
    public Category save(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Override
    public Optional<Category> findByCategoryId(Long categoryId) {
        Category category = entityManager.find(Category.class, categoryId);
        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> findAll() {
        List<Category> result = entityManager.createQuery("select c from Category  c", Category.class).getResultList();
        return result;
    }

    @Override
    public Optional<Category> findByCategoryName(String categoryName) {
        List<Category> category = entityManager.createQuery("select c from Category c where c.categoryName = :categoryName", Category.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
        return category.stream().findAny();
    }
}
