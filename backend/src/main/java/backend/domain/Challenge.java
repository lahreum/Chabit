package backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Challenge { // Challenge 코드리뷰 필수.

    @Id
    @GeneratedValue
    private Long challengeId;

    @Column(nullable = false)
    private String challengeName;
    @Column(columnDefinition = "TEXT")
    private String challengeDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="challenge_owner", nullable = false)
    private User challengeOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category challengeCategory;


    @Column(nullable = false)
    private LocalDateTime challengeStartdate;
    @Column(nullable = false)
    private LocalDateTime challengeEnddate;

    @Column(columnDefinition = "TEXT")
    private String challengeThumbnail;
    @Column
    @ColumnDefault("100")
    private int challengePoint;
    @Column
    @ColumnDefault("1")
    private int challengeUsercount;

    @Column
    private String authWay;
    @Column(nullable = false)
    private String authFrequency;
    @Column
    private LocalTime authStarttime;
    @Column
    private LocalTime authEndtime;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private int authHoliday; //tinyint int로 했음.
    @Column(columnDefinition = "TEXT")
    private String authExample;

    @Enumerated(EnumType.STRING) //저장할 때.. 정하자
    private ChallengeOngoing challengeOngoing;
}
/*
CREATE TABLE IF NOT EXISTS `chabit`.`CHALLENGE` (
  `CHALLENGE_ID` BIGINT NOT NULL, ㅇ
  `CHALLENGE_NAME` VARCHAR(255) NOT NULL, ㅇ
  `CHALLENGE_DESC` TEXT NULL, ㅇ
  `CHALLENGE_OWNER` BIGINT NOT NULL, ㅇ
  `CHALLENGE_CATEGORY` BIGINT NULL,ㅇ
  `CHALLENGE_STARTDATE` DATETIME NOT NULL,ㅇ
  `CHALLENGE_ENDDATE` DATETIME NOT NULL,ㅇ
  `CHALLENGE_THUMBNAIL` TEXT NULL,ㅇ
  `CHALLENGE_POINT` INT NULL DEFAULT 100,ㅇ
  `CHALLENGE_USERCOUNT` INT NULL DEFAULT 1,ㅇ
  `AUTH_WAY` VARCHAR(255) NULL,ㅇ
  `AUTH_FREQUENCY` VARCHAR(255) NOT NULL,
  `AUTH_TIME` TIME NULL,
  `AUTH_HOLIDAY` TINYINT NOT NULL,
  `AUTH_EXAMPLE` TEXT NULL,
  `CHALLENGE_ONGOING` VARCHAR(45) NULL DEFAULT 'READY',
  PRIMARY KEY (`CHALLENGE_ID`),
  INDEX `CHALLENGE_USER_FK_idx` (`CHALLENGE_OWNER` ASC) VISIBLE,
  INDEX `CHALLENGE_CATEGORY_FK_idx` (`CHALLENGE_CATEGORY` ASC) VISIBLE,
  CONSTRAINT `CHALLENGE_USER_FK`
    FOREIGN KEY (`CHALLENGE_OWNER`)
    REFERENCES `chabit`.`USER` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CHALLENGE_CATEGORY_FK`
    FOREIGN KEY (`CHALLENGE_CATEGORY`)
    REFERENCES `chabit`.`CATEGORY` (`CATEGORY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
 */