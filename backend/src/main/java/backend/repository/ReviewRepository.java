package backend.repository;

import backend.domain.Review;
import backend.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {
    private final EntityManager entityManager;

    public Review saveReview(Review review){
        entityManager.persist(review);
        return review;
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
}
