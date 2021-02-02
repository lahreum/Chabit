package backend.repository;

import backend.domain.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPALevelRepository implements LevelRepository{

    private final EntityManager entityManager;


    @Override
    public Level save(Level level) {
        entityManager.persist(level);
        return level;
    }

    @Override
    public List<Level> findAll() {
        List<Level> result = entityManager.createQuery("select l from Level l",Level.class).getResultList();
        return result;
    }
}
