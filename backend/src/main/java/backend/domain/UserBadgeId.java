package backend.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class UserBadgeId implements Serializable {
    private User user;
    private Badge badge;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBadgeId that = (UserBadgeId) o;
        return user.equals(that.user) && badge.equals(that.badge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, badge);
    }
}
