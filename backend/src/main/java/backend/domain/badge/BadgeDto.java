package backend.domain.badge;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BadgeDto {
    private String badgeName;
    private String badgeType;
    private String badgeCategory;
    private String badgeImage;

    public BadgeDto (Badge badge) {
        this.badgeName = badge.getBadgeName();
        this.badgeType = badge.getBadgeType().equals(BadgeType.BRONZE) ? "BRONZE" :  (badge.getBadgeType().equals(BadgeType.SILVER) ? "SILVER" : "GOLD");
        this.badgeCategory = badge.getBadgeCategory().getCategoryName();
        this.badgeImage = badge.getBadgeImage();
    }
}
