package backend.domain.follow;

import backend.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(FollowId.class)
@Getter @Setter
public class Follow {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User userId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="FOLLOWING_ID")
    private User followingId;

    private LocalDateTime followDate;
}
