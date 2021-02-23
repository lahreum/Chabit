package backend.repository;

import backend.domain.review.Review;
import backend.domain.review.ReviewComment;
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

    public void deleteReviewComment(ReviewComment reviewComment){entityManager.remove(reviewComment);}

    public Long findChildCommentsCount(Long reviewCommentId) {
        return entityManager.createQuery("select COUNT(rc) from ReviewComment rc where rc.parentCommentId.reviewCommentId = :reviewCommentId", Long.class)
                .setParameter("reviewCommentId", reviewCommentId)
                .getSingleResult();
    }
}
