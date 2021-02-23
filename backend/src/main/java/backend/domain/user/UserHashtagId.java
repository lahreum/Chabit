package backend.domain.user;

import backend.domain.hashtag.Hashtag;

import java.io.Serializable;
import java.util.Objects;

public class UserHashtagId implements Serializable {
    private User user;
    private Hashtag hashtag;

    public UserHashtagId(){}

    public UserHashtagId(User user, Hashtag hashtag) {
        this.user = user;
        this.hashtag = hashtag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHashtagId that = (UserHashtagId) o;
        return user.equals(that.user) && hashtag.equals(that.hashtag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, hashtag);
    }
}
