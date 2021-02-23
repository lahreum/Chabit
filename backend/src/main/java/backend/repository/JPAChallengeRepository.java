package backend.repository;

import backend.domain.challenge.Challenge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPAChallengeRepository implements ChallengeRepoistory{

    private final EntityManager entityManager;


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
                ("select c from Challenge c order by c.challengeUsercount desc", Challenge.class)
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

    @Override
    public Challenge findByChallengeId(Long challengeId) {
        return entityManager.find(Challenge.class, challengeId);
    }

}
