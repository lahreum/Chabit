package backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userEmail;
    private String userNickname;
    private String userName;
    private String userPhone;
    private int userPoints;
    private LocalDateTime userJoindate;

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
}
