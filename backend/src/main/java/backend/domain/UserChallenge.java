package backend.domain;

import javax.persistence.*;

@Entity
@IdClass(UserChallengeId.class)
public class UserChallenge {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="challenge_id")
    private Challenge challenge;
}
