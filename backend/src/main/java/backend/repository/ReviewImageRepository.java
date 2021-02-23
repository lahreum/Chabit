package backend.repository;

import backend.domain.review.Review;
import backend.domain.review.ReviewImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewImageRepository {
    private final EntityManager entityManager;

    public ReviewImage saveReviewImage(ReviewImage reviewImage){
        entityManager.persist(reviewImage);
        return reviewImage;
    }

    public Optional<ReviewImage> findReviewImageThumbnailByReviewId(Review reviewId){
        List<ReviewImage> reviewImageList = entityManager.createQuery("select ri from ReviewImage ri where ri.reviewId =:reviewId", ReviewImage.class)
                .setParameter("reviewId", reviewId)
                .getResultList();
        return reviewImageList.stream().findFirst();
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
