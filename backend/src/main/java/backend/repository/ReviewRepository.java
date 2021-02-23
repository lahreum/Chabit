package backend.repository;

import backend.domain.review.Review;
import backend.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {
    private final EntityManager entityManager;

    public Review saveReview(Review review){
        entityManager.persist(review);
        return review;
    }

    public Optional<Review> findByUserIdAndChallengeId(Long userId, Long challengeId) {
        List<Review> resultList = entityManager.createQuery("select r from Review r where r.userId.userId = :userId and r.challengeId.challengeId = :challengeId", Review.class)
                .setParameter("userId", userId)
                .setParameter("challengeId", challengeId)
                .getResultList();
        return resultList.stream().findAny();
    }

    public List<Review> findByUserIdOrderByReviewDate(User user){
        List<Review> reviewList = entityManager.createQuery
                ("select r from Review r where r.userId =:userId order by r.reviewDate desc", Review.class)
                .setParameter("userId", user)
                .getResultList();
        return reviewList;
    }

    public Review findByReviewId(Long reviewId) {
        Review reviewResult = entityManager.find(Review.class, reviewId);
        return reviewResult;
    }
    public List<Review> findAll(){
        List<Review> reviewList = entityManager.createQuery(
                "select r from Review r",Review.class)
                .getResultList();
        return reviewList;
    }
    public void deleteReview(Review review) {
        entityManager.remove(review);
    }

    public List<Review> findAllOrderByCoolCount() {
        List<Review> resultList = entityManager.createQuery("select r from Review r order by r.coolCount desc", Review.class)
                .setMaxResults(4)
                .getResultList();
        return resultList;
    }
}
