package backend.domain.review;

import backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ReviewComment {

    @Id @GeneratedValue
    @Column(name = "review_comment_id")
    private Long reviewCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private ReviewComment parentCommentId;

    @OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewComment> childrenComment;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int commentOrder = 0;

    // 연관관계
    public void addReply(ReviewComment reply) {
        this.childrenComment.add(reply);
        reply.setParentCommentId(this);
    }

    public static ReviewComment createCommentReply(Review reviewId, User user, ReviewComment parent, String commentContent) {
        ReviewComment reply = new ReviewComment();
        reply.setReviewId(reviewId);
        reply.setUserId(user);
        reply.setCommentContent(commentContent);
        reply.setParentCommentId(parent);

        return reply;
    }
}

/**
 `parent_comment_id` BIGINT NULL DEFAULT NULL,
 INDEX `REVIEW_COMMENT_REVIEW_COMMENT_FK_idx` (`parent_comment_id` ASC) VISIBLE,
 CONSTRAINT `REVIEW_COMMENT_REVIEW_COMMENT_FK`
 FOREIGN KEY (`parent_comment_id`)
 */
