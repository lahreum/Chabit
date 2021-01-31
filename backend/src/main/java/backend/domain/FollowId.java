package backend.domain;

import java.io.Serializable;
import java.util.Objects;

public class FollowId implements Serializable {
    private User userId;
    private User followingId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowId followId = (FollowId) o;
        return userId.equals(followId.userId) && followingId.equals(followId.followingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, followingId);
    }
}
