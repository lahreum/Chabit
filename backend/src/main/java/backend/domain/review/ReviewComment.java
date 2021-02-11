package backend.domain.review;

import backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ReviewComment {

    @Id
    @GeneratedValue
    private Long reviewCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentContent;

    //Todo : 대댓글 작성 컬럼 추가 예정.
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "review_comment_id", referencedColumnName = "parent_comment_id")
//    private ReviewComment parentCommentId;
//
//    @OneToMany(mappedBy = "parentCommentId")
//    private List<ReviewComment> childrenComment;

    @Column(columnDefinition = "INT DEFAULT NULL")
    private int commentOrder;

}

/**
 `parent_comment_id` BIGINT NULL DEFAULT NULL,
 INDEX `REVIEW_COMMENT_REVIEW_COMMENT_FK_idx` (`parent_comment_id` ASC) VISIBLE,
 CONSTRAINT `REVIEW_COMMENT_REVIEW_COMMENT_FK`
 FOREIGN KEY (`parent_comment_id`)
 */
