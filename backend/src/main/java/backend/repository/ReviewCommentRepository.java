package backend.repository;

import backend.domain.Review;
import backend.domain.ReviewComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewCommentRepository {
    private final EntityManager entityManager;

    public ReviewComment saveReviewComment(ReviewComment reviewComment){
        entityManager.persist(reviewComment);
        return reviewComment;
    }

    public ReviewComment findReviewCommentByReviewCommentId(Long reviewCommentId){
        ReviewComment reviewComment = entityManager.find(ReviewComment.class, reviewCommentId);
        return reviewComment;
    }

    public List<ReviewComment> findReviewCommentByReviewId(Review reviewId){
        List<ReviewComment> result =
                entityManager.createQuery("select rc from ReviewComment rc where rc.reviewId =:reviewId", ReviewComment.class)
                        .setParameter("reviewId", reviewId)
                        .getResultList();
        return result;
    }
}
