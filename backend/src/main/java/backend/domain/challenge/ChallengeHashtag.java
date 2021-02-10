package backend.domain.challenge;

import backend.domain.hashtag.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(ChallengeHashtagId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ChallengeHashtag {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CHALLENGE_ID")
    private Challenge challenge;

    @Id
    @ManyToOne
    @JoinColumn(name="HASHTAG_ID")
    private Hashtag hashtag;
}
