package backend.domain.badge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@ApiModel
public class BadgeResponse {
    @ApiModelProperty(value="뱃지 목록")
    private List<BadgeDto> badge = new ArrayList<>();

    public void addBadge(Badge badge, boolean userGet) {
        this.badge.add(new BadgeDto(badge, userGet));
    }

}
