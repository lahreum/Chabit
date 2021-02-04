package backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {
    private String userEmail;
    private String userNickname;
    private String userName;
    private String userPhone;
    private int userPoints;
    private LocalDateTime userJoindate;
    private HashtagDto hashtags;
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
