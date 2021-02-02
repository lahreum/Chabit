package backend.repository;

import backend.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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
    public List<Category> findAll() {
        List<Category> result = entityManager.createQuery("select c from Category  c", Category.class).getResultList();
        return result;
    }
}
