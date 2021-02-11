package backend.domain.follow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class FollowDto {
    @ApiModelProperty(value = "유저 이메일")
    private String userEmail;
    @ApiModelProperty(value = "유저 닉네임")
    private String userNickname;
    @ApiModelProperty(value = "유저 프로필 사진")
    private String userImage;
}
