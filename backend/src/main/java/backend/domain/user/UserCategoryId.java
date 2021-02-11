package backend.domain.user;

import backend.domain.challenge.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class UserCategoryId implements Serializable {
    private User user;
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCategoryId that = (UserCategoryId) o;
        return user.equals(that.user) && category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, category);
    }
}
