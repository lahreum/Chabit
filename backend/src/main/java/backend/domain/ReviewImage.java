package backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ReviewImage {
    @Id
    @GeneratedValue
    private Long reviewImageId;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID", columnDefinition = "BIGINT NULL DEFAULT NULL")
    private Review reviewId;

    @Column(columnDefinition = "TEXT DEFAULT NULL")
    private String reviewImage;
}
