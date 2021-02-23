package backend.domain.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentDto {
    Long reviewCommentId;
    Long userId;
    Long reviewId;
    String userImage;
    String userNickname;
    String userLevelImage;
    String commentContent;
    List<ReviewCommentDto> replies = new ArrayList<>();

    public ReviewCommentDto(ReviewComment reviewComment){
        this.reviewId = reviewComment.getReviewId().getReviewId();
        this.userId = reviewComment.getUserId().getUserId();
        this.userImage = reviewComment.getUserId().getUserImage();
        this.userNickname = reviewComment.getUserId().getUserNickname();
        this.reviewCommentId = reviewComment.getReviewCommentId();
        this.commentContent = reviewComment.getCommentContent();
    }

    public void addReply(ReviewCommentDto reply) {
        this.replies.add(reply);
    }
}
