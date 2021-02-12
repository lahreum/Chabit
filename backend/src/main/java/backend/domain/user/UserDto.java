package backend.domain.user;

import backend.domain.badge.BadgeResponse;
import backend.domain.hashtag.HashtagDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ApiModel(value = "UserDto : 유저에 대한 정보", description = "현재 찾은 유저의 정보를 나타냄.")
public class UserDto {
    @ApiModelProperty(value="사용자 이메일")
    private String userEmail;
    @ApiModelProperty(value="사용자 패스워드")
    private String userPassword;
    @ApiModelProperty(value="사용자 닉네임")
    private String userNickname;
    @ApiModelProperty(value="사용자 이름")
    private String userName;
    @ApiModelProperty(value="사용자 휴대폰 번호")
    private String userPhone;
    @ApiModelProperty(value="사용자 프로필 메세지")
    private String userProfileMessage;
    @ApiModelProperty(value="사용자 포인트")
    private int userPoints;
    @ApiModelProperty(value="사용자 가입날짜")
    private LocalDateTime userJoindate;
    @ApiModelProperty(value="사용자 해시태그")
    private HashtagDto hashtags;
    @ApiModelProperty(value="사용자 레벨")
    private LevelDto userLevel;
    @ApiModelProperty(value="사용자 권한")
    private String userRole;
    @ApiModelProperty(value="사용자 프로필사진")
    private String userImage;
    @ApiModelProperty(value="사용자 획득 뱃지 목록")
    private BadgeResponse badges;

    public UserDto(String userEmail, String userNickname, String userName, String userPhone) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userPhone = userPhone;
    }
    public UserDto(User user) {
        this.userEmail = user.getUserEmail();
        this.userNickname = user.getUserNickname();
        this.userName = user.getUserName();
        this.userPhone = user.getUserPhone();
        this.userPoints = user.getUserPoints();
        this.userJoindate = user.getUserJoindate();
        this.userImage = user.getUserImage();
        this.userRole = user.getUserRole().equals(UserRole.USER) ? "USER" : "ADMIN";
        this.userProfileMessage = user.getUserProfileMessage();
    }

    public UserDto(User user, int userPoint) {
        this.userEmail = user.getUserEmail();
        this.userNickname = user.getUserNickname();
        this.userName = user.getUserName();
        this.userPhone = user.getUserPhone();
        this.userPoints = user.getUserPoints();
        this.userJoindate = user.getUserJoindate();
        this.userImage = user.getUserImage();
        this.userRole = user.getUserRole().equals(UserRole.USER) ? "USER" : "ADMIN";
        this.userProfileMessage = user.getUserProfileMessage();
        this.userPoints = userPoint;
    }

    public UserDto(String userEmail, String userNickname, String userName, String userPhone, int userPoints, LocalDateTime userJoindate) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPoints = userPoints;
        this.userJoindate = userJoindate;
    }

    public UserDto(String userEmail, String userPassword, String userNickname, String userName, String userPhone, int userPoints, LocalDateTime userJoindate, UserRole userRole) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPoints = userPoints;
        this.userJoindate = userJoindate;
        this.userRole = userRole.equals(UserRole.USER) ? "USER" : "ADMIN";
    }

    public void addHashtags(HashtagDto hashtags) {
        this.hashtags = hashtags;
    }
    public void addUserLevel(LevelDto levelDto) {
        this.userLevel = levelDto;
    }
    public void addBadges(BadgeResponse badges) {
        this.badges = badges;
    }
}
