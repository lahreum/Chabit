package backend.domain;

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
    @ApiModelProperty(value="사용자 닉네임")
    private String userNickname;
    @ApiModelProperty(value="사용자 이름")
    private String userName;
    @ApiModelProperty(value="사용자 휴대폰 번호")
    private String userPhone;
    @ApiModelProperty(value="사용자 포인트")
    private int userPoints;
    @ApiModelProperty(value="사용자 가입날짜")
    private LocalDateTime userJoindate;
    @ApiModelProperty(value="사용자 해시태그")
    private HashtagDto hashtags;
    @ApiModelProperty(value="사용자 레벨")
    private String userLevel;
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
    }

    public UserDto(String userEmail, String userNickname, String userName, String userPhone, int userPoints, LocalDateTime userJoindate) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPoints = userPoints;
        this.userJoindate = userJoindate;
    }

    public void addHashtags(HashtagDto hashtags) {
        this.hashtags = hashtags;
    }
    public void addUserLevel(String userLevel) {this.userLevel = userLevel;}
}
