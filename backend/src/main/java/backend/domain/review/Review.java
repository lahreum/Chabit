package backend.domain.review;

import backend.domain.challenge.Challenge;
import backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long reviewId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reviewContent;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime reviewDate;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int coolCount;
    @Column(columnDefinition = "INT DEFAULT 0")
    private int commentCount;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name ="user_id")
     private User userId;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name ="challenge_id")
     private Challenge challengeId;

     @OneToMany(mappedBy = "reviewId", cascade = CascadeType.ALL)
     private List<ReviewImage> reviewImageList = new ArrayList<>();

    @OneToMany(mappedBy = "reviewId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cool> coolList = new ArrayList<>();

     public static Review createReview(User userId, Challenge challengeId, String reviewContent){
         Review review = new Review();
         review.reviewContent = reviewContent;
         review.challengeId = challengeId;
         review.userId = userId;
         review.reviewDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
         return review;
     }

    // 연관관계
    public void addReviewImage(ReviewImage reviewImage) {
         this.reviewImageList.add(reviewImage);
         reviewImage.setReviewId(this);
    }

    // 멋저요 누르기
    public void pressCool(Cool cool) {
        this.coolList.add(cool);
        cool.setReviewId(this);
        this.coolCount += 1; // 멋져요 수 증가
    }

    // 멋져요 취소
    public void unpressCool(Cool cool) {
        this.coolList.remove(cool);
        this.coolCount -= 1; // 멋져요 수 감소
    }

    public void upCommentCount(){
        this.commentCount +=1;
    }
    public void downCommentCount(){
        this.commentCount -=1;
    }

}
