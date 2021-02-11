package backend.domain.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentDto {
    Long reviewCommentId;
    Long userId;
    Long reviewId;
    String commentContent;

    public ReviewCommentDto(ReviewComment reviewComment){
        this.reviewId = reviewComment.getReviewId().getReviewId();
        this.userId = reviewComment.getUserId().getUserId();
        this.reviewCommentId = reviewComment.getReviewCommentId();
        this.commentContent = reviewComment.getCommentContent();
    }
}
