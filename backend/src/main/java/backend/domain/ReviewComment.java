package backend.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
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

    @Column(columnDefinition = "BIGINT DEFAULT NULL")
    private ReviewComment parentCommentId;

    @Column(columnDefinition = "INT DEFAULT NULL")
    private int commentOrder;

}