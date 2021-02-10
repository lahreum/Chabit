package backend.domain.badge;

import backend.domain.challenge.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ApiModel
public class Badge {

    @Id @GeneratedValue
    @ApiModelProperty(value = "뱃지 id")
    private Long badgeId;

    @ApiModelProperty(value = "뱃지 이름")
    private String badgeName;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "뱃지 타입(금, 은, 동)")
    private BadgeType badgeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ApiModelProperty(value = "뱃지가 속한 카테고리")
    private Category badgeCategory;

    @Column(columnDefinition = "TEXT")
    private String badgeImage;
}
