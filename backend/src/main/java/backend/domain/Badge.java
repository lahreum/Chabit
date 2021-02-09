package backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Badge {
    @Id @GeneratedValue
    private Long badgeId;
    private String badgeName;

    @Enumerated(EnumType.STRING)
    private BadgeType badgeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category badgeCategory;
}
