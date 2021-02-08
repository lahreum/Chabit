package backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String reviewContent;
    private LocalDateTime reviewDate;
    private int coolCount;
    private int commentCount;
    private User userId;
    private Challenge challengeId;
    private ReviewImageDto reviewImageDto;
    public ReviewDto(Review review){
        this.reviewId = review.getReviewId();
        this.reviewContent = review.getReviewContent();
        this.reviewDate = review.getReviewDate();
        this.coolCount = review.getCoolCount();
        this.commentCount = review.getCommentCount();
        this.userId = review.getUserId();
        this.challengeId = review.getChallengeId();
    }

    public void addReviewImage(ReviewImageDto reviewImageDto){
        this.reviewImageDto = reviewImageDto;
    }
}
