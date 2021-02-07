package backend.service;

import backend.domain.Review;
import backend.domain.ReviewImage;
import backend.domain.User;
import backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * 리뷰 저장
     * @return 저장한 리뷰 정보
     */
    @Transactional
    public Review saveReview(Review review){
        return reviewRepository.saveReview(review);
    }
    /**
     * 아이디값에 따른 reviewList가져오기
     * @return 아이디에 맞는 reviewList
     */
    public List<Review> findByUserIdOrderByReviewDate(User user){
        return reviewRepository.findByUserIdOrderByReviewDate(user);
    }
    /**
     * 해당 리뷰에 대한 상세정보
     * @return 리뷰 상세 정보
     */
    public Review findByReviewId(Long reviewId){
        return reviewRepository.findByReviewId(reviewId);
    }

    public ReviewImage
}
