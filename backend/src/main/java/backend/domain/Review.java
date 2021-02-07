package backend.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
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

}
