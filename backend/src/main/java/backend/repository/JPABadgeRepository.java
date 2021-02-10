package backend.repository;

import backend.domain.badge.Badge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPABadgeRepository implements BadgeRepository{
    private final EntityManager entityManager;

    @Override
    public List<Badge> findByCategoryId(Long categoryId) {
        List<Badge> resultList = entityManager.createQuery("select b from Badge b where b.badgeCategory.categoryId = :categoryId", Badge.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
        return resultList;
    }

    @Override
    public List<Badge> findAll() {
        List<Badge> resultList = entityManager.createQuery("select b from Badge b", Badge.class)
                .getResultList();
        return resultList;
    }
}
