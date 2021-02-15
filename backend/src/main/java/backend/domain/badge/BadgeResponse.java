package backend.domain.badge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ApiModel
public class BadgeResponse {
    @ApiModelProperty(value="유저가 딴 동뱃지")
    private int userGetBronze;
    @ApiModelProperty(value="유저가 딴 은뱃지")
    private int userGetSilver;
    @ApiModelProperty(value="유저가 딴 금뱃지")
    private int userGetGold;
    @ApiModelProperty(value="뱃지 목록")
    private Map<String, List<BadgeDto>> badge = new HashMap<>();

    public void addBadge(Badge badge, boolean userGet) {
        if (userGet) {
            // 유저가 뱃지 딴 경우 횟수 증가
            if(badge.getBadgeType().equals(BadgeType.BRONZE))
                userGetBronze += 1;
            else if (badge.getBadgeType().equals(BadgeType.SILVER))
                userGetSilver += 1;
            else
                userGetGold += 1;
        }

        String categoryName = badge.getBadgeCategory().getCategoryName();
        if (!this.badge.containsKey(categoryName)) {
            this.badge.put(categoryName, new ArrayList<>());
        }
        this.badge.get(categoryName).add(new BadgeDto(badge, userGet));
    }

}
