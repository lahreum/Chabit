package backend.domain.challenge;


import backend.domain.hashtag.HashtagDto;
import backend.domain.user.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data //@ToString @Getter , @Setter, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor //파라미터가 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@ApiModel
public class ChallengeDto {
    @ApiModelProperty(value = "챌린지 아이디")
    private Long challengeID;
    @ApiModelProperty(value = "챌린지 이름")
    private String challengeName;
    @ApiModelProperty(value = "챌린지 설명")
    private String challengeDesc;
    @ApiModelProperty(value = "챌린지 개설자")
    private UserDto challengeOwner;
    @ApiModelProperty(value = "챌린지 카테고리")
    private String categoryName;
    @ApiModelProperty(value = "챌린지 해시태그")
    private HashtagDto hashtags;
    @ApiModelProperty(value = "챌린지 시작일")
    private LocalDateTime challengeStartDate;
    @ApiModelProperty(value = "챌린지 종료일")
    private LocalDateTime challengeEndDate;
    @ApiModelProperty(value = "챌린지 썸네일")
    private String challengeThumbnail;
    @ApiModelProperty(value = "챌린지 완료시 획득 포인트")
    private int challengePoint;
    @ApiModelProperty(value = "챌린지 참여 인원")
    private int challengeUserCount;
    @ApiModelProperty(value = "인증 방법")
    private String authWay;
    @ApiModelProperty(value = "인증 빈도(주 n회)")
    private int authFrequency;
    @ApiModelProperty(value = "인증 시작 시간")
    private LocalTime authStarttime;
    @ApiModelProperty(value = "인증 마감 시간")
    private LocalTime authEndtime;
    @ApiModelProperty(value = "공휴일 인증 여부 (true / false)")
    private boolean authHoliday;
    @ApiModelProperty(value = "인증샷 예시")
    private String authExample;
    @ApiModelProperty(value = "챌린지 상태 (예정 / 진행/ 종료)")
    private ChallengeOngoing ongoing;

    @ApiModelProperty(value = "유저 - 챌린지 상태 (참가 안함 / 참가 중/ 성공 / 실패")
    private String challengeJoinStatus;
    @ApiModelProperty(value = "리뷰 작성 여부 (작성 함 / 아직 안함 / 할 수 없음)")
    private String reviewStatus;

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

    public void addHashtag(HashtagDto hashtags) {
        this.hashtags = hashtags;
    }

    public void addUserStatus(String challengeJoinStatus, String reviewStatus) {
        this.challengeJoinStatus = challengeJoinStatus;
        this.reviewStatus = reviewStatus;
    }

    public void addChallengeStatus(String challengeJoinStatus) {
        this.challengeJoinStatus = challengeJoinStatus;
    }
}
