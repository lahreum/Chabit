package backend.repository;

import backend.domain.follow.Follow;
import backend.domain.follow.FollowId;
import backend.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
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

    public List<Follow> findByUserId(User user) {
        List<Follow> followingList = entityManager.createQuery("select f from Follow f where f.userId = :userId", Follow.class)
                .setParameter("userId", user)
                .getResultList();
        return followingList;
    }

    public List<Follow> findByFollowingId(User user) {
        List<Follow> followerList = entityManager.createQuery("select f from Follow f where f.followingId = :userId", Follow.class)
                .setParameter("userId", user)
                .getResultList();
        return followerList;
    }

    public void deleteFollow(Follow follow) {
        entityManager.remove(follow);
    }
}
