package backend.repository;

import backend.domain.Review;
import backend.domain.ReviewImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewImageRepository {
    private final EntityManager entityManager;

    public ReviewImage saveReviewImage(ReviewImage reviewImage){
        entityManager.persist(reviewImage);
        return reviewImage;
    }

    public List<ReviewImage> findReviewImageByReviewId(Review reviewId){
        List<ReviewImage> reviewImageList = entityManager.createQuery("select ri from ReviewImage ri where ri.reviewId =:reviewId", ReviewImage.class)
                .setParameter("reviewId", reviewId)
                .getResultList();
        return reviewImageList;
    }

    public void deleteReviewImage(ReviewImage reviewImage){
        entityManager.remove(reviewImage);
    }
}
