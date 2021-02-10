package backend.domain.follow;

import backend.domain.user.User;

import java.io.Serializable;
import java.util.Objects;

public class FollowId implements Serializable {
    private User userId;
    private User followingId;

    public FollowId() {
    }

    public FollowId(User userId, User followingId) {
        this.userId = userId;
        this.followingId = followingId;
    }

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
