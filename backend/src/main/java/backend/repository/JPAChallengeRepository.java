package backend.repository;

import backend.domain.Challenge;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPAChallengeRepository implements ChallengeRepoistory{

    private final EntityManager entityManager;

    @Autowired
    public JPAChallengeRepository(EntityManager entityManager) {this.entityManager = entityManager;}


    @Override
    public Challenge save(Challenge challenge) {
        entityManager.persist(challenge);
        return challenge;
    }

    @Override
    public List<Challenge> findByChallengeNameLikeContaining(String challengeName) {
        String jsql  = "select c from Challenge c where c.challengeName like :challengeName";
        List<Challenge> result = entityManager.createQuery(jsql,Challenge.class)
                .setParameter("challengeName", "%" + challengeName + "%")
                .getResultList();
        return result;
    }


    @Override
    public List<Challenge> findAllOrderByChallengeUserCount() {
        List<Challenge> result = entityManager.createQuery
                ("select c from Challenge c order by c.challengeUserCount desc", Challenge.class)
                .setFirstResult(0)
                .setMaxResults(4)
                .getResultList();
        return result;
    }

    @Override
    public List<Challenge> findAll() {
        List<Challenge> result = entityManager.createQuery("select c from Challenge c",Challenge.class)
                .getResultList();
        return result;
    }

}
