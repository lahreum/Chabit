package backend.domain.user;

import backend.domain.challenge.Challenge;
import backend.domain.challenge.ChallengeResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserChallengeId.class)
@Getter @Setter
public class UserChallenge {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="challenge_id")
    private Challenge challenge;

    @Enumerated(EnumType.STRING)
    private ChallengeResult userChallengeResult;

    public UserChallenge(User user, Challenge challenge) {
        this.user = user;
        this.challenge = challenge;
        this.userChallengeResult = ChallengeResult.READY;
    }
}
