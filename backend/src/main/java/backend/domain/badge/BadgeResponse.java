package backend.domain.badge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@ApiModel
public class BadgeResponse {
    @ApiModelProperty(value="뱃지 전체 목록")
    private List<BadgeDto> badges = new ArrayList<>();
    @ApiModelProperty(value="유저가 획득한 뱃지")
    private List<BadgeDto> userBadges = new ArrayList<>();

    public void addBadge(Badge badge) {
        this.badges.add(new BadgeDto(badge));
    }

    public void addUserBadge(Badge badge) {
        this.userBadges.add(new BadgeDto(badge));
    }

}
