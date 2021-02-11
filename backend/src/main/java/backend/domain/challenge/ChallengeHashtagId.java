package backend.domain.challenge;

import backend.domain.hashtag.Hashtag;

import java.io.Serializable;
import java.util.Objects;

public class ChallengeHashtagId implements Serializable {
    private Challenge challenge;
    private Hashtag hashtag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeHashtagId that = (ChallengeHashtagId) o;
        return challenge.equals(that.challenge) && hashtag.equals(that.hashtag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(challenge, hashtag);
    }
}
