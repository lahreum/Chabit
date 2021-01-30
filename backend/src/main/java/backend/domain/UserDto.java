package backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String userEmail;
    private String userNickname;
    private String userName;
    private String userPhone;
}
