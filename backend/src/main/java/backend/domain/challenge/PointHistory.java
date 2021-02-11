package backend.domain.challenge;

import backend.domain.challenge.Challenge;
import backend.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class PointHistory {
    @Id @GeneratedValue
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    private LocalDateTime pointDate;
    private int pointChange;

    public PointHistory(User user, Challenge challenge, LocalDateTime pointDate, int pointChange) {
        this.user = user;
        this.challenge = challenge;
        this.pointDate = pointDate;
        this.pointChange = pointChange;
    }
}
