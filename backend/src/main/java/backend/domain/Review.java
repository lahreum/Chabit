package backend.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private Timestamp dateTime;

    @Column
    @ColumnDefault("0")
    private int coolCount;
    @Column
    @ColumnDefault("0")
    private int commentCount;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name ="user_id")
     private User userId;

//    @Column
//    @ColumnDefault("null")
//    private Long userId;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name ="challenge_id")
     private Challenge challengeId;


//    @Column
//    @ColumnDefault("null")
//    private Long challengeId;

}

/**
 *   `review_id` BIGINT NOT NULL,
 *   `review_content` TEXT NOT NULL,
 *   `review_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
 *   `cool_count` INT NULL DEFAULT '0',
 *   `comment_count` INT NULL DEFAULT '0',
 *   `user_id` BIGINT NULL DEFAULT NULL,
 *   `challenge_id` BIGINT NULL DEFAULT NULL,
 *   PRIMARY KEY (`review_id`),
 *   INDEX `REVIEW_USER_FK_idx` (`user_id` ASC) VISIBLE,
 *   INDEX `REVIEW_CHALLENGE_FK_idx` (`challenge_id` ASC) VISIBLE,
 *   CONSTRAINT `REVIEW_CHALLENGE_FK`
 *     FOREIGN KEY (`challenge_id`)
 *     REFERENCES `chabit`.`challenge` (`challenge_id`),
 *   CONSTRAINT `REVIEW_USER_FK`
 *     FOREIGN KEY (`user_id`)
 *     REFERENCES `chabit`.`user` (`user_id`))
 * ENGINE = InnoDB
 * DEFAULT CHARACTER SET = utf8mb4
 * COLLATE = utf8mb4_0900_ai_ci;
 */