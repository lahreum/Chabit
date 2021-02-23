package backend.domain.user;

import backend.domain.challenge.Challenge;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class UserChallengeId implements Serializable {
    private User user;
    private Challenge challenge;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChallengeId that = (UserChallengeId) o;
        return user.equals(that.user) && challenge.equals(that.challenge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, challenge);
    }
}
