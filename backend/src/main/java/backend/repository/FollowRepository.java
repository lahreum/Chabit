package backend.repository;

import backend.domain.Follow;
import backend.domain.FollowId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class FollowRepository {

    private final EntityManager entityManager;

    public Follow follow(Follow follow) {
        entityManager.persist(follow);
        return follow;
    }

    public Optional<Follow> findByUserIdAndFollowingId(Follow follow) {
        FollowId followId = new FollowId(follow.getUserId(), follow.getFollowingId());
        Follow findFollow = entityManager.find(Follow.class, followId);

        return Optional.ofNullable(findFollow);
    }

}
