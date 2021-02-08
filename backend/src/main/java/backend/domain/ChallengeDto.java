package backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data //@ToString @Getter , @Setter, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor //파라미터가 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
public class ChallengeDto {
    private Long challengeID;
    private String challengeName;
    private String challengeDesc;
    private UserDto challengeOwner;
    private String categoryName;
    private LocalDateTime challengeStartDate;
    private LocalDateTime challengeEndDate;
    private String challengeThumbnail;
    private int challengePoint;
    private int challengeUserCount;
    private String authWay;
    private int authFrequency;
    private LocalTime authStarttime;
    private LocalTime authEndtime;
    private boolean authHoliday;
    private String authExample;
    private ChallengeOngoing ongoing;


    ////////////추가적으로 생성하자.
    public ChallengeDto (Challenge challenge){
        this.challengeID = challenge.getChallengeId();
        this.challengeName = challenge.getChallengeName();
        this.challengeDesc = challenge.getChallengeDesc();
        this.challengeOwner = new UserDto(challenge.getChallengeOwner());
        this.categoryName = challenge.getChallengeCategory().getCategoryName();
        this.challengeStartDate = challenge.getChallengeStartdate();
        this.challengeEndDate = challenge.getChallengeEnddate();
        this.challengeThumbnail = challenge.getChallengeThumbnail();
        this.challengePoint = challenge.getChallengePoint();
        this.challengeUserCount = challenge.getChallengeUsercount();
        this.authWay = challenge.getAuthWay();
        this.authFrequency = challenge.getAuthFrequency();
        this.authStarttime = challenge.getAuthStarttime();
        this.authEndtime = challenge.getAuthEndtime();
        this.authHoliday = challenge.getAuthHoliday() == 1;
        this.authExample = challenge.getAuthExample();
        this.ongoing = challenge.getChallengeOngoing();
    }

    // 메인화면 HOT 챌린지.
    public ChallengeDto (String challengeName, String categoryName, UserDto challengeOwner, String challengeThumbnail, int authFrequency){
        this.challengeName = challengeName;
        this.categoryName = categoryName;
        this.challengeOwner = challengeOwner;
        this.challengeThumbnail = challengeThumbnail;
        this.authFrequency = authFrequency;
    }
}
