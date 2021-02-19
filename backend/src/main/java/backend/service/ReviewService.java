package backend.service;

import backend.controller.CommentRequest;
import backend.domain.review.Cool;
import backend.domain.review.Review;
import backend.domain.review.ReviewComment;
import backend.domain.review.ReviewImage;
import backend.domain.user.User;
import backend.repository.ReviewCommentRepository;
import backend.repository.ReviewImageRepository;
import backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewImageRepository reviewImageRepository;

    private final ReviewCommentRepository reviewCommentRepository;
    /**
     * 리뷰 저장
     * @return 저장한 리뷰 정보
     */
    @Transactional
    public Review saveReview(Review review){
        return reviewRepository.saveReview(review);
    }

    @Transactional
    public void addReviewImages(Review review, List<String> urls) {
        for (String url : urls) {
            review.addReviewImage(new ReviewImage(review, url));
        }
    }

    /**
     * 유저아이디, 챌린지 아이디로 리뷰 찾기
     */
    public Optional<Review> findByUserIdAndChallengeId(Long userId, Long challengeId) {
        return reviewRepository.findByUserIdAndChallengeId(userId, challengeId);
    }

    /**
     * 아이디값에 따른 reviewList가져오기
     * @return 아이디에 맞는 reviewList
     */
    public List<Review> findByUserIdOrderByReviewDate(User user){
        return reviewRepository.findByUserIdOrderByReviewDate(user);
    }

    /**
     * 인기 리뷰 4개 조회
     */
    public List<Review> findAllOrderByCoolCount() {
        return reviewRepository.findAllOrderByCoolCount();
    }

    /**
     * 해당 리뷰에 대한 상세정보
     * @return 리뷰 상세 정보
     */
    public Review findByReviewId(Long reviewId){
        return reviewRepository.findByReviewId(reviewId);
    }

    /**
     * 해당 리뷰에 대한 리뷰 사진 저장
     * @return은 하나에 대한 리뷰 정보
     */
    @Transactional
    public ReviewImage saveReviewImage(ReviewImage reviewImage){
        return reviewImageRepository.saveReviewImage(reviewImage);
    }

    /**
     * 해당 reivewId에 해당하는 리뷰 이미지들을 다 받아옴.
     * @param reviewId
     * @return
     */
    public List<ReviewImage> findReviewImageByReviewId(Review reviewId){
        List<ReviewImage> reviewImageList = reviewImageRepository.findReviewImageByReviewId(reviewId);
        return reviewImageList;
    }

    public ReviewImage findReviewImageThumbnailByReviewId(Review reviewId){
        Optional<ReviewImage> reviewImage = reviewImageRepository.findReviewImageThumbnailByReviewId(reviewId);
        if(!reviewImage.isPresent()){
            throw new IllegalStateException("이미지가 없습니다.");
        }
        else{
            return reviewImage.get();
        }
    }


    @Transactional
    public ReviewComment saveReviewComment(ReviewComment reviewComment){
        reviewComment.getReviewId().upCommentCount();
        return reviewCommentRepository.saveReviewComment(reviewComment);
    }

    public ReviewComment findReviewCommentByReviewCommentId(Long reviewCommentId){
        return reviewCommentRepository.findReviewCommentByReviewCommentId(reviewCommentId);
    }

    public List<ReviewComment> findReviewCommentByReviewId(Review reviewId){
        List<ReviewComment> result = reviewCommentRepository.findReviewCommentByReviewId(reviewId);
        return result;
    }

    @Transactional
    public ReviewComment updateReviewComment(ReviewComment newReviewComment){
        ReviewComment reviewComment = reviewCommentRepository
                .findReviewCommentByReviewCommentId(newReviewComment.getReviewCommentId());
        reviewComment
                .setCommentContent(newReviewComment.getCommentContent());
        reviewCommentRepository.saveReviewComment(reviewComment);
        return reviewComment;
    }

    @Transactional
    public void deleteReviewComment(ReviewComment reviewComment){
        Long childCommentsCount = reviewCommentRepository.findChildCommentsCount(reviewComment.getReviewCommentId());
        for (long i = 0; i < childCommentsCount; ++i) {
            reviewComment.getReviewId().downCommentCount();
        }
        reviewComment.getReviewId().downCommentCount();
        reviewCommentRepository.deleteReviewComment(reviewComment);
    }


    /**
     * remove, findALl()
     */
    public void deleteReview(Review review){ reviewRepository.deleteReview(review); }

    public void deleteReviewImage(ReviewImage reviewImage){
        reviewImageRepository.deleteReviewImage(reviewImage);
    }

    public List<Review> findAll() { return reviewRepository.findAll(); }

    @Transactional
    public void pressCool(Review review, User user) {
        List<Cool> coolList = review.getCoolList();
        for (Cool c : coolList) {
            if (c.getUserId().getUserEmail().equals(user.getUserEmail())) {
                // 이미 멋져요 누름 -> 멋져요 취소
                review.unpressCool(c);
                return;
            }
        }
        review.pressCool(new Cool(user, review));
    }

    @Transactional
    public void saveCommentReply(Review review, User user, ReviewComment parent, CommentRequest request) {
        review.upCommentCount();
        parent.addReply(ReviewComment.createCommentReply(review, user, parent, request.getCommentContent()));
    }
}
