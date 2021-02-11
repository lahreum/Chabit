package backend.domain.badge;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BadgeDto {
    private String badgeName;
    private String badgeType;
    private String badgeCategory;
    private boolean userGet;
    private String badgeImage;

    public BadgeDto (Badge badge, boolean userGet) {
        this.badgeName = badge.getBadgeName();
        this.badgeType = badge.getBadgeType().equals(BadgeType.BRONZE) ? "BRONZE" :  (badge.getBadgeType().equals(BadgeType.SILVER) ? "SILVER" : "GOLD");
        this.badgeCategory = badge.getBadgeCategory().getCategoryName();
        this.userGet = userGet;
        if (userGet)
            this.badgeImage = badge.getBadgeImage();
        else
            this.badgeImage = "https://ssafychabit.s3.ap-northeast-2.amazonaws.com/badges/g12-office-block_97340.png"; // 기본 이미지
    }
}
