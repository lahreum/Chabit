package backend.domain.user;

import backend.domain.badge.Badge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class UserBadge {
    @Id @GeneratedValue
    private Long userBadgeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id")
    private Badge badge;

    public UserBadge(User user, Badge badge) {
        this.user = user;
        this.badge = badge;
    }
}
