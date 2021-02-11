package backend.domain.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ReviewImage {
    @Id
    @GeneratedValue
    private Long reviewImageId;

    @ManyToOne
    @JoinColumn(name = "REVIEW_ID")
    private Review reviewId;

    @Column(columnDefinition = "TEXT")
    private String reviewImage;

    public ReviewImage(Review review, String url) {
        this.reviewId = review;
        this.reviewImage = url;
    }
}
