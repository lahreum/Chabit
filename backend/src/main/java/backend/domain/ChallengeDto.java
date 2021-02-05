package backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data //@ToString @Getter , @Setter, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor //파라미터가 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
public class ChallengeDto {
    private Long challengeID;
    private String challengeName;
    private String challengeDesc;
    private Long challengeOwner;
    private Long challengeCategory;
    private LocalDateTime challengeStartDate;
    private LocalDateTime challengeEndDate;
    private String challengeThumbnail;
    private int challengePoint;
    private int challengeUserCount;
    private String authWay;
    private String authFrequency;
    private LocalDateTime authTime;
    private int authHoliday;
    private String authExample;


    ////////////추가적으로 생성하자.
    // 메인화면 HOT 챌린지.
    public ChallengeDto (String challengeName, Long challengeCategory, long challengeOwner, String challengeThumbnail, String authFrequency){
        this.challengeName = challengeName;
        this.challengeCategory = challengeCategory;
        this.challengeOwner = challengeOwner;
        this.challengeThumbnail = challengeThumbnail;
        this.authFrequency = authFrequency;
    }
}
