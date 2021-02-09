package backend.repository;

import backend.domain.ReviewComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ReviewCommentRepository {
    private final EntityManager entityManager;

    public ReviewComment saveReviewComment(ReviewComment reviewComment){
        entityManager.persist(reviewComment);
        return reviewComment;
    }

    public ReviewComment findByReviewCommentId(Long reviewCommentId){
        ReviewComment reviewComment = entityManager.find(ReviewComment.class, reviewCommentId);
        return reviewComment;
    }
}
